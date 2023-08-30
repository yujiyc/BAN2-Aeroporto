package persistencia;

import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dados.*;
import excecoes.*;

public class ControladorAereoDAO {
    
    private static ControladorAereoDAO instance = null;
    private static MongoDatabase conexao = null;
    private MongoCollection<Document> controladores = null;

    public static ControladorAereoDAO getInstance() throws MongoException {
        if(instance == null){
            instance = new ControladorAereoDAO();
        }
        return instance;
    }

    private ControladorAereoDAO() throws MongoException {
        conexao = Conexao.getConexao();
    }

    public void insert(ControladorAereo controladorAereo) throws InsertException{
        try {
            controladores = conexao.getCollection("controladorAereo");
            
            // sindicato
            Document sind = new Document("_id", controladorAereo.getSindicato().getNroMembro())
            .append("nome", controladorAereo.getSindicato().getNome());
            
            // controlador
            Document doc = new Document("_id", new ObjectId()) //matricula
            .append("nome", controladorAereo.getNome())
            .append("endereco", controladorAereo.getEndereco())
            .append("salario", controladorAereo.getSalario())
            .append("telefone", controladorAereo.getTelefone())
            .append("dataExame", controladorAereo.getDataExame())
            .append("sindicato", sind);
            
            controladores.insertOne(doc);
        } catch (MongoException e) {
            throw new InsertException("Controlador Aereo");
        }
    }

    public void delete(ControladorAereo controladorAereo) throws DeleteException {
        try {
            controladores = conexao.getCollection("controladorAereo");

            Bson query = eq("_id", controladorAereo.getMatricula());

            controladores.deleteOne(query);
        } catch (MongoException e) {
            throw new DeleteException("Controlador Aereo");
        }
    }

    public ControladorAereo select(ObjectId matricula) throws SelectException {

        try {

            controladores = conexao.getCollection("controladorAereo");
            
            Bson query = eq("_id", matricula);

            Document doc = controladores.find(query).first();

            Document sind = doc.get("sindicato", org.bson.Document.class);

            Sindicato sindicato = new Sindicato(sind.getObjectId("_id"), sind.getString("nome"));

            ControladorAereo controladorAereo = new ControladorAereo(matricula, doc.getString("nome"), doc.getString("endereco"), 
            doc.getDouble("salario"), doc.getString("telefone"), sindicato, doc.getString("dataExame"));

            return controladorAereo;

        } catch (MongoException e) {
            throw new SelectException("Controlador Aereo");
        }

    }

    public List<ControladorAereo> selectAll() throws SelectException {

        try {

            List<ControladorAereo> lista = new LinkedList<>();

            controladores = conexao.getCollection("controladorAereo");

            FindIterable<Document> docs = controladores.find();

            for(Document d: docs){
                Document sind = d.get("sindicato", org.bson.Document.class);

                Sindicato sindicato = new Sindicato(sind.getObjectId("_id"), sind.getString("nome"));

                ControladorAereo controladorAereo = new ControladorAereo(d.getObjectId("_id"), d.getString("nome"), d.getString("endereco"), 
                d.getDouble("salario"), d.getString("telefone"), sindicato, d.getString("dataExame"));

                lista.add(controladorAereo);
            }
         
            return lista;
        } catch (MongoException e) {
            throw new SelectException("Controladores Aereos");
        }

    }

}

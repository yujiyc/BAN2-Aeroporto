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

public class EmpregadoDAO {
    
    private static EmpregadoDAO instance = null;
    private static MongoDatabase conexao = null;
    private MongoCollection<Document> empregados = null;


    public static EmpregadoDAO getInstance() throws MongoException{

        if(instance == null){
            instance = new EmpregadoDAO();
        }

        return instance;
    }

    private EmpregadoDAO() throws MongoException {
        conexao = Conexao.getConexao();
    }

    public void insert(Empregado empregado) throws InsertException {
        try {
            empregados = conexao.getCollection("empregados");
            
            // sindicato
            Document sind = new Document("_id", empregado.getSindicato().getNroMembro())
            .append("nome", empregado.getSindicato().getNome());
            
            // empregado
            Document doc = new Document("_id", new ObjectId()) // matricula
            .append("nome", empregado.getNome())
            .append("endereco", empregado.getEndereco())
            .append("salario", empregado.getSalario())
            .append("telefone", empregado.getTelefone())
            .append("sindicato", sind);
            
            empregados.insertOne(doc);
        } catch (MongoException e) {
            throw new InsertException("Empregado");
        }
    }

    public void delete(Empregado empregado) throws DeleteException {
        try {
            empregados = conexao.getCollection("empregados");

            Bson query = eq("_id", empregado.getMatricula());

            empregados.deleteOne(query);
        } catch (MongoException e) {
            throw new DeleteException("Empregado");
        }
    }

    public Empregado select(ObjectId matricula) throws SelectException {

        try {

            empregados = conexao.getCollection("empregados");

            Bson query = eq("_id", matricula);

            Document doc = empregados.find(query).first();

            Document sind = doc.get("sindicato", org.bson.Document.class);

            Sindicato sindicato = new Sindicato(sind.getObjectId("_id"), sind.getString("nome"));

            Empregado empregado = new Empregado(matricula, doc.getString("nome"), doc.getString("endereco"), 
            doc.getDouble("salario"), doc.getString("telefone"), sindicato);

            return empregado;

        } catch (MongoException e) {
            throw new SelectException("Empregado");
        }

    }

    public List<Empregado> selectAll() throws SelectException {

        try {

            List<Empregado> lista = new LinkedList<>();

            empregados = conexao.getCollection("empregados");

            FindIterable<Document> docs = empregados.find();

            for(Document d: docs){

                Document sind = d.get("sindicato", org.bson.Document.class);

                Sindicato sindicato = new Sindicato(sind.getObjectId("_id"), sind.getString("nome"));

                Empregado empregado = new Empregado(d.getObjectId("_id"), d.getString("nome"), d.getString("endereco"), 
                d.getDouble("salario"), d.getString("telefone"), sindicato);

                lista.add(empregado);
            }
            
            return lista;

        } catch (MongoException e) {
            throw new SelectException("Empregados");
        }

    }
}

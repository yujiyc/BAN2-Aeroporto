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

public class TesteDAO {
    
    private static TesteDAO instance = null;
    private static MongoDatabase conexao = null;
    private MongoCollection<Document> testes = null;


    public static TesteDAO getInstance() throws MongoException {

        if(instance == null){
            instance = new TesteDAO();
        }

        return instance;
    }

    private TesteDAO() throws MongoException {
        conexao = Conexao.getConexao();
    }

    public void insert(Teste teste) throws InsertException {
        try {
            testes = conexao.getCollection("teste");
            
            // modelo
            Document mod = new Document("_id", teste.getModelo().getCodigo())
            .append("nome", teste.getModelo().getNome())
            .append("capacidade", teste.getModelo().getCapacidade())
            .append("peso", teste.getModelo().getPeso());
            
            // teste
            Document doc = new Document("_id", new ObjectId()) //idAnac
            .append("nome", teste.getNome())
            .append("pontMax", teste.getPontMax())
            .append("modelo", mod);
            
            testes.insertOne(doc);
        } catch (MongoException e) {
            throw new InsertException("Teste");
        }
    }

    public void delete(Teste teste) throws DeleteException {

        try {
            testes = conexao.getCollection("teste");

            Bson query = eq("_id", teste.getIdAnac());

            testes.deleteOne(query);
        } catch (MongoException e) {
            throw new DeleteException("Teste");
        }
    }

    public Teste select(ObjectId idAnac) throws SelectException {

        try {
            testes = conexao.getCollection("teste");
            
            Bson query = eq("_id", idAnac);

            Document doc = testes.find(query).first();

            Document mod = doc.get("modelo", org.bson.Document.class);
            Modelo modelo = ModeloDAO.getInstance().select(mod.getObjectId("_id"));

            Teste teste = new Teste(idAnac, doc.getString("nome"), doc.getDouble("pontMax"), modelo);

            return teste;

        } catch (MongoException e) {
            throw new SelectException("Teste");
        }

    }

    public List<Teste> selectAll() throws SelectException {

        try {

            List<Teste> lista = new LinkedList<>();

            testes = conexao.getCollection("teste");

            FindIterable<Document> docs = testes.find();

            for(Document d: docs){
                Document mod = d.get("modelo", org.bson.Document.class);
                Modelo modelo = ModeloDAO.getInstance().select(mod.getObjectId("_id"));

                Teste teste = new Teste(d.getObjectId("_id"), d.getString("nome"), d.getDouble("pontMax"), modelo);

                lista.add(teste);
            }
        
            return lista;

        } catch (MongoException e) {
            throw new SelectException("Testes");
        }

    }
}

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

public class ModeloDAO {
    
    private static ModeloDAO instance = null;
    private static MongoDatabase conexao = null;
    private MongoCollection<Document> modelos = null;

    public static ModeloDAO getInstance() throws MongoException {
        if(instance == null){
            instance = new ModeloDAO();
        }
        return instance;
    }

    private ModeloDAO() throws MongoException {
        conexao = Conexao.getConexao();
    }

    public void insert(Modelo modelo) throws SelectException, InsertException {
        try {
            modelos = conexao.getCollection("modelo");

            // modelo
            Document doc = new Document("_id", new ObjectId()) // codigo
            .append("nome", modelo.getNome())
            .append("capacidade", modelo.getCapacidade())
            .append("peso", modelo.getPeso());

            modelos.insertOne(doc);
        } catch (MongoException e) {
            throw new InsertException("Modelo");
        }
    }

    public void delete(Modelo modelo) throws DeleteException {
        try {
            modelos = conexao.getCollection("modelo");

            Bson query = eq("_id", modelo.getCodigo());

            modelos.deleteOne(query);
        } catch (MongoException e) {
            throw new DeleteException("Modelo");
        }
    }

    public Modelo select(ObjectId codigo) throws SelectException {

        try {

            modelos = conexao.getCollection("modelo");

            Bson query = eq("_id", codigo);

            Document doc = modelos.find(query).first();

            Modelo modelo = new Modelo(codigo, doc.getString("nome"), doc.getInteger("capacidade"), doc.getDouble("peso"));

            return modelo;

        } catch (MongoException e) {
            throw new SelectException("Modelo");
        }

    }

    public List<Modelo> selectAll() throws SelectException {

        try {
           
            modelos = conexao.getCollection("modelo");

            FindIterable<Document> docs = modelos.find();

            List<Modelo> lista = new LinkedList<>();

            for(Document d: docs){
                Modelo modelo = new Modelo(d.getObjectId("_id"), d.getString("nome"), d.getInteger("capacidade"), d.getDouble("peso"));
                lista.add(modelo);
            }

            return lista;
        } catch (MongoException e) {
            throw new SelectException("Modelos");
        }

    }
    
}

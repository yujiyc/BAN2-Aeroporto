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

public class TecnicoDAO {

    private static TecnicoDAO instance = null;
    private static MongoDatabase conexao = null;
    private MongoCollection<Document> tecnicos = null;

    public static TecnicoDAO getInstance() throws MongoException {
        if(instance == null){
            instance = new TecnicoDAO();
        }
        return instance;
    }

    private TecnicoDAO() throws MongoException {
        conexao = Conexao.getConexao();
    }

    public void insert(Tecnico tecnico) throws InsertException{
        try {
            tecnicos = conexao.getCollection("tecnicos");
            
            // sindicato
            Document sind = new Document("_id", tecnico.getSindicato().getNroMembro())
            .append("nome", tecnico.getSindicato().getNome());
            
            // tecnico
            Document doc = new Document("_id", new ObjectId()) // matricula
            .append("nome", tecnico.getNome())
            .append("endereco", tecnico.getEndereco())
            .append("salario", tecnico.getSalario())
            .append("telefone", tecnico.getTelefone())
            .append("modelos", converteEmDocumentos(new LinkedList<>()))
            .append("sindicato", sind);
            
            tecnicos.insertOne(doc);
        } catch (MongoException e) {
            throw new InsertException("Tecnico");
        }
    }

    public void delete(Tecnico tecnico) throws DeleteException {
        try {
            tecnicos = conexao.getCollection("tecnicos");

            Bson query = eq("_id", tecnico.getMatricula());

            tecnicos.deleteOne(query);
        } catch (MongoException e) {
            throw new DeleteException("Tecnico");
        }
    }

    public Tecnico select(ObjectId matricula) throws SelectException {

        try {
            tecnicos = conexao.getCollection("tecnicos");

            Bson query = eq("_id", matricula);

            Document doc = tecnicos.find(query).first();

            Document sind = doc.get("sindicato", org.bson.Document.class);

            Sindicato sindicato = new Sindicato(sind.getObjectId("_id"), sind.getString("nome"));

            List<Modelo> modelos = selectModelosTec(matricula);

            Tecnico tecnico = new Tecnico(matricula, doc.getString("nome"), doc.getString("endereco"), 
            doc.getDouble("salario"), doc.getString("telefone"), sindicato, modelos);

            return tecnico;

        } catch (MongoException e) {
            throw new SelectException("Tecnico");
        }
    }

    private List<Modelo> selectModelosTec(ObjectId matricula) throws SelectException {

        try {

            List<Modelo> lista = new LinkedList<>();

            tecnicos = conexao.getCollection("tecnicos");

            Bson query = eq("_id", matricula);

            Document doc = tecnicos.find(query).first();

            for(Document m: doc.getList("modelos", org.bson.Document.class)){
                
                Modelo modelo = new Modelo(m.getObjectId("_id"), m.getString("nome"), m.getInteger("capacidade"), m.getDouble("peso"));

                lista.add(modelo);
            }

            return lista;

        } catch (MongoException e) {
            throw new SelectException("Modelos");
        }
    }

    public List<Tecnico> selectAll() throws SelectException {

        try {
            
            List<Tecnico> lista = new LinkedList<>();

            tecnicos = conexao.getCollection("tecnicos");

            FindIterable<Document> docs = tecnicos.find();

            for(Document d: docs){
                Document sind = d.get("sindicato", org.bson.Document.class);

                Sindicato sindicato = new Sindicato(sind.getObjectId("_id"), sind.getString("nome"));

                List<Modelo> modelos = selectModelosTec(d.getObjectId("_id"));

                Tecnico tecnico = new Tecnico(d.getObjectId("_id"), d.getString("nome"), d.getString("endereco"), 
                d.getDouble("salario"), d.getString("telefone"), sindicato, modelos);

                lista.add(tecnico);
            }

            return lista;

        } catch (MongoException e) {
            throw new SelectException("Tecnico");
        }

    }

    public void insereModelo(Tecnico tecnico, Modelo modelo) throws SelectException, InsertException {

        try {
            tecnicos = conexao.getCollection("tecnicos");

            tecnico.getModelos().add(modelo);

            Document sind = new Document("_id", tecnico.getSindicato().getNroMembro())
            .append("nome", tecnico.getSindicato().getNome());
            
            // tecnico
            Document doc = new Document("_id", tecnico.getMatricula()) // matricula
            .append("nome", tecnico.getNome())
            .append("endereco", tecnico.getEndereco())
            .append("salario", tecnico.getSalario())
            .append("telefone", tecnico.getTelefone())
            .append("modelos", converteEmDocumentos(tecnico.getModelos()))
            .append("sindicato", sind);

            Document m = new Document("$set", doc);

            Bson query = eq("_id", tecnico.getMatricula());

            tecnicos.updateOne(query, m);

        } catch (MongoException e) {
            throw new InsertException("Modelo para o Tecnico!");
        }
    }

    public void deleteModelo(Tecnico tecnico, Modelo modelo) throws DeleteException {

        try {

            tecnicos = conexao.getCollection("tecnicos");

            tecnico.getModelos().remove(modelo);

            Document sind = new Document("_id", tecnico.getSindicato().getNroMembro())
            .append("nome", tecnico.getSindicato().getNome());
            
            // tecnico
            Document doc = new Document("_id", tecnico.getMatricula()) // matricula
            .append("nome", tecnico.getNome())
            .append("endereco", tecnico.getEndereco())
            .append("salario", tecnico.getSalario())
            .append("telefone", tecnico.getTelefone())
            .append("modelos", converteEmDocumentos(tecnico.getModelos()))
            .append("sindicato", sind);

            Document m = new Document("$set", doc);

            Bson query = eq("_id", tecnico.getMatricula());

            tecnicos.updateOne(query, m);

        } catch (MongoException e) {
            throw new DeleteException("Perito_em");
        }
    }

    private List<Document> converteEmDocumentos(List<Modelo> modelos){

        if(modelos.isEmpty()){
            return new LinkedList<Document>();
        }

        List<Document> docs = new LinkedList<>();

        for(Modelo m: modelos){

            Document doc = new Document("_id", m.getCodigo()) // codigo
            .append("nome", m.getNome())
            .append("capacidade", m.getCapacidade())
            .append("peso", m.getPeso());

            docs.add(doc);
        }

        return docs;
    }

}

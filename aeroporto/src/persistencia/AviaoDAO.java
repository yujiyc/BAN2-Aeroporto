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

public class AviaoDAO {

    private static AviaoDAO instance = null;
    private static MongoDatabase conexao = null;
    private MongoCollection<Document> avioes = null;


    public static AviaoDAO getInstance() throws MongoException {
        if(instance == null){
            instance = new AviaoDAO();
        }
        return instance;
    }

    private AviaoDAO() throws MongoException {
        conexao = Conexao.getConexao();
    }

    public void insert(Aviao aviao) throws InsertException {
        try {
            avioes = conexao.getCollection("avioes");

            // modelo
            Document mod = new Document("_id", aviao.getModelo().getCodigo())
            .append("nome", aviao.getModelo().getNome())
            .append("capacidade", aviao.getModelo().getCapacidade())
            .append("peso", aviao.getModelo().getPeso());

            // aviao
            Document doc = new Document("_id", new ObjectId()) // nroRegistro
            .append("nome", aviao.getNome())
            .append("modelo", mod);

            avioes.insertOne(doc);
        } catch (MongoException e) {
            throw new InsertException("Aviao");
        }
    }

    public void delete(Aviao aviao) throws DeleteException {
        try {
            avioes = conexao.getCollection("avioes");

            Bson query = eq("_id", aviao.getNroRegistro());

            avioes.deleteOne(query);
        } catch (MongoException e) {
            throw new DeleteException("Aviao");
        }
    }

    public void update(Aviao aviao) throws UpdateException {
        try {
            // chama a tabela
            avioes = conexao.getCollection("avioes");

            Document mod = new Document("_id", aviao.getModelo().getCodigo())
            .append("nome", aviao.getModelo().getNome())
            .append("capacidade", aviao.getModelo().getCapacidade())
            .append("peso", aviao.getModelo().getPeso());

            Document sub = new Document("nome", aviao.getNome()).append("modelo", mod);

            // cria o documento que será substituirá
            Document doc = new Document("$set", sub);

            // cria a busca utilizando o método "eq()" do "Filters"
            Bson query = eq("_id", aviao.getNroRegistro());

            // atualiza a informação no banco
            avioes.updateOne(query, doc);
        } catch (MongoException e) {
            throw new UpdateException("Aviao");
        }
    }

    public Aviao select(ObjectId nroRegistro) throws SelectException {

        try {
            // chama a tabela
            avioes = conexao.getCollection("avioes");

            // cria a busca utilizando o método "eq()" do "Filters"
            Bson query = eq("_id", nroRegistro);

            // acha o documento para transformar
            Document doc = avioes.find(query).first();

            Document mod = doc.get("modelo", org.bson.Document.class);

            Modelo modelo = new Modelo(mod.getObjectId("_id"), mod.getString("nome"), mod.getInteger("capacidade"), mod.getDouble("peso"));

            // transforma o documento em um aviao
            Aviao aviao = new Aviao(nroRegistro, doc.getString("nome"), modelo);

            return aviao;

        } catch (MongoException e) {
            throw new SelectException("Aviao");
        }

    }

    public List<Aviao> selectAll() throws SelectException {

        try {

            List<Aviao> lista = new LinkedList<>();

            avioes = conexao.getCollection("avioes");

            FindIterable<Document> docs = avioes.find();

            for(Document d: docs){

                Document mod = d.get("modelo", org.bson.Document.class);

                Modelo modelo = new Modelo(mod.getObjectId("_id"), mod.getString("nome"), mod.getInteger("capacidade"), mod.getDouble("peso"));

                Aviao aviao = new Aviao(d.getObjectId("_id"), d.getString("nome"), modelo);

                lista.add(aviao);
            }

            return lista;

        } catch (MongoException e) {
            throw new SelectException("Avioes");
        }

    }
}

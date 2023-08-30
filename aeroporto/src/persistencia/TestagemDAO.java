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

public class TestagemDAO {
    
    private static TestagemDAO instance = null;
    private static MongoDatabase conexao = null;
    private MongoCollection<Document> testagens = null;
    
    public static TestagemDAO getInstance() throws MongoException {
        
        if(instance == null){
            instance = new TestagemDAO();
        }
        
        return instance;
    }
    
    private TestagemDAO() throws MongoException {
        conexao = Conexao.getConexao();
    }

    public void insert(Testagem testagem) throws SelectException, InsertException {
        try {
            testagens = conexao.getCollection("testagem");

            // aviao
            Document mod = new Document("_id", testagem.getAviao().getModelo().getCodigo())
            .append("nome", testagem.getAviao().getModelo().getNome())
            .append("capacidade", testagem.getAviao().getModelo().getCapacidade())
            .append("peso", testagem.getAviao().getModelo().getPeso());

            Document avi = new Document("_id", testagem.getAviao().getNroRegistro())
            .append("nome", testagem.getAviao().getNome())
            .append("modelo", mod);

            // tecnico
            Document sind = new Document("_id", testagem.getTecnico().getSindicato().getNroMembro())
            .append("nome", testagem.getTecnico().getSindicato().getNome());

            Document tecn = new Document("_id", testagem.getTecnico().getMatricula())
            .append("nome", testagem.getTecnico().getNome())
            .append("endereco", testagem.getTecnico().getEndereco())
            .append("salario", testagem.getTecnico().getSalario())
            .append("telefone", testagem.getTecnico().getTelefone())
            .append("sindicato", sind);

            // teste
            Document mod_test = new Document("_id", testagem.getTeste().getModelo().getCodigo())
            .append("nome", testagem.getTeste().getModelo().getNome())
            .append("capacidade", testagem.getTeste().getModelo().getCapacidade())
            .append("peso", testagem.getTeste().getModelo().getPeso());
            
            Document test = new Document("_id", testagem.getTeste().getIdAnac())
            .append("nome", testagem.getTeste().getNome())
            .append("pontMax", testagem.getTeste().getPontMax())
            .append("modelo", mod_test);

            // testagem
            Document doc = new Document("_id", new ObjectId()) //id
            .append("aviao", avi)
            .append("tecnico", tecn)
            .append("teste", test);

            testagens.insertOne(doc);
        } catch (MongoException e) {
            throw new InsertException("Testagem");
        }
    }

    public void delete(Testagem testagem) throws DeleteException {
        try {
            testagens = conexao.getCollection("testagem");

            Bson query = eq("_id", testagem.getId());

            testagens.deleteOne(query);
        } catch (MongoException e) {
            throw new DeleteException("Testagem");
        }

    }

    public Testagem select(ObjectId id) throws SelectException {

        try {
            testagens = conexao.getCollection("testagem");

            Bson query = eq("_id", id);

            Document doc = testagens.find(query).first();

            Document tec = doc.get("tecnico", org.bson.Document.class);

            Tecnico tecnico = TecnicoDAO.getInstance().select(tec.getObjectId("_id"));

            Document avi = doc.get("aviao", org.bson.Document.class);

            Aviao aviao = AviaoDAO.getInstance().select(avi.getObjectId("_id"));

            Document tes = doc.get("teste", org.bson.Document.class);

            Teste teste = TesteDAO.getInstance().select(tes.getObjectId("_id"));

            Testagem testagem = new Testagem(id, tecnico, aviao, teste);

            return testagem;

        } catch (MongoException e) {
            throw new SelectException("Testagem");
        }

    }

    public List<Testagem> selectAll() throws SelectException {

        try {

            List<Testagem> lista = new LinkedList<>();

            testagens = conexao.getCollection("testagem");

            FindIterable<Document> docs = testagens.find();

            for(Document d: docs){

                Document tec = d.get("tecnico", org.bson.Document.class);

                Tecnico tecnico = TecnicoDAO.getInstance().select(tec.getObjectId("_id"));

                Document avi = d.get("aviao", org.bson.Document.class);

                Aviao aviao = AviaoDAO.getInstance().select(avi.getObjectId("_id"));

                Document tes = d.get("teste", org.bson.Document.class);

                Teste teste = TesteDAO.getInstance().select(tes.getObjectId("_id"));

                Testagem testagem = new Testagem(d.getObjectId("_id"), tecnico, aviao, teste);

                lista.add(testagem);
            }

            return lista;
            
        } catch (MongoException e) {
            throw new SelectException("Testagens");
        }

    }

}

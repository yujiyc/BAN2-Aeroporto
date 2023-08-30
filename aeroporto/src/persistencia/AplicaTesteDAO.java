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

public class AplicaTesteDAO {
    
    private static AplicaTesteDAO instance = null;
    private static MongoDatabase conexao = null;
    private MongoCollection<Document> aplicatestes = null;

    public static AplicaTesteDAO getInstance() throws MongoException {

        if(instance == null){
            instance = new AplicaTesteDAO();
        }

        return instance;
    }

    private AplicaTesteDAO() throws MongoException {
        conexao = Conexao.getConexao();
    }

    public void insert(AplicaTeste aplicaTeste) throws InsertException, SelectException {

        try {
            aplicatestes = conexao.getCollection("aplicaTeste");

            // tecnico
            Document sind = new Document("_id", aplicaTeste.getTecnico().getSindicato().getNroMembro())
            .append("nome", aplicaTeste.getTecnico().getSindicato().getNome());

            Document tecn = new Document("_id", aplicaTeste.getTecnico().getMatricula())
            .append("nome", aplicaTeste.getTecnico().getNome())
            .append("endereco", aplicaTeste.getTecnico().getEndereco())
            .append("salario", aplicaTeste.getTecnico().getSalario())
            .append("telefone", aplicaTeste.getTecnico().getTelefone())
            .append("sindicato", sind);

            // teste
            Document mod_test = new Document("_id", aplicaTeste.getTeste().getModelo().getCodigo())
            .append("nome", aplicaTeste.getTeste().getModelo().getNome())
            .append("capacidade", aplicaTeste.getTeste().getModelo().getCapacidade())
            .append("peso", aplicaTeste.getTeste().getModelo().getPeso());
            
            Document test = new Document("_id", aplicaTeste.getTeste().getIdAnac())
            .append("nome", aplicaTeste.getTeste().getNome())
            .append("pontMax", aplicaTeste.getTeste().getPontMax())
            .append("modelo", mod_test);

            // aplicaTeste
            Document doc = new Document("_id", new ObjectId()) // id
            .append("data", aplicaTeste.getData())
            .append("pontObtida", aplicaTeste.getPontObtida())
            .append("horasGastas", aplicaTeste.getHorasGastas())
            .append("teste", test)
            .append("tecnico", tecn);

            aplicatestes.insertOne(doc);
        } catch (MongoException e) {
            throw new InsertException("Aplicacao do Teste");
        }
    }

    public void delete(AplicaTeste aplicaTeste) throws DeleteException {
        try {
            aplicatestes = conexao.getCollection("aplicaTeste");

            Bson query = eq("_id", aplicaTeste.getId());

            aplicatestes.deleteOne(query);
        } catch (MongoException e) {
            throw new DeleteException("Aplicacao do Teste");
        }
    }

    public AplicaTeste select(ObjectId id) throws SelectException {

        try {
            aplicatestes = conexao.getCollection("aplicaTeste");

            Bson query = eq("_id", id);

            Document doc = aplicatestes.find(query).first();

            Document tes = doc.get("teste", org.bson.Document.class);

            Document mod = tes.get("modelo", org.bson.Document.class);

            Modelo modelo = new Modelo(mod.getObjectId("_id"), mod.getString("nome"), mod.getInteger("capacidade"), mod.getDouble("peso"));

            Teste teste = new Teste(tes.getObjectId("_id"), tes.getString("nome"), tes.getDouble("pontMax"), modelo);

            Document tec = doc.get("tecnico", org.bson.Document.class);

            Document sind = tec.get("sindicato", org.bson.Document.class);

            Sindicato sindicato = new Sindicato(sind.getObjectId("_id"), sind.getString("nome"));

            Tecnico tecnico = new Tecnico(tec.getObjectId("_id"), tec.getString("nome"), tec.getString("endereco"), 
            tec.getDouble("salario"), tec.getString("telefone"), sindicato);

            AplicaTeste aplicaTeste = new AplicaTeste(id, teste, tecnico, doc.getString("data"), doc.getDouble("pontObtida"), doc.getInteger("horasGastas"));

            return aplicaTeste;

        } catch (MongoException e) {
            throw new SelectException("Aplicacao do Teste");
        }

    }

    public List<AplicaTeste> selectAll() throws SelectException {

        try {

            List<AplicaTeste> lista = new LinkedList<>();
            
            aplicatestes = conexao.getCollection("aplicaTeste");

            FindIterable<Document> docs = aplicatestes.find();

            for(Document d: docs){
                Document tes = d.get("teste", org.bson.Document.class);

                Document mod = tes.get("modelo", org.bson.Document.class);

                Modelo modelo = new Modelo(mod.getObjectId("_id"), mod.getString("nome"), mod.getInteger("capacidade"), mod.getDouble("peso"));

                Teste teste = new Teste(tes.getObjectId("_id"), tes.getString("nome"), tes.getDouble("pontMax"), modelo);

                Document tec = d.get("tecnico", org.bson.Document.class);

                Document sind = tec.get("sindicato", org.bson.Document.class);

                Sindicato sindicato = new Sindicato(sind.getObjectId("_id"), sind.getString("nome"));

                Tecnico tecnico = new Tecnico(tec.getObjectId("_id"), tec.getString("nome"), tec.getString("endereco"), 
                tec.getDouble("salario"), tec.getString("telefone"), sindicato);

                AplicaTeste aplicaTeste = new AplicaTeste(d.getObjectId("_id"), teste, tecnico, d.getString("data"), d.getDouble("pontObtida"), d.getInteger("horasGastas"));
            
                lista.add(aplicaTeste);
            }

            return lista;

        } catch (MongoException e) {
            throw new SelectException("Aplicacoes de Teste");
        }

    }


}

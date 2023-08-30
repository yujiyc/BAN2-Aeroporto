package persistencia;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dados.*;
import excecoes.*;

public class SindicatoDAO {
    private static SindicatoDAO instance = null;
    private static MongoDatabase conexao = null;
    private MongoCollection<Document> sindicatos = null;
    
    public static SindicatoDAO getInstance() throws MongoException {
        if(instance == null){
            instance = new SindicatoDAO();
        }
        return instance;
    }
    
    private SindicatoDAO() throws MongoException {
        conexao = Conexao.getConexao();
    }

    public void insert(Sindicato sindicato) throws InsertException {
        try {
            // chama a tabela
            sindicatos = conexao.getCollection("sindicatos");
            
            // cria o documento que será inserido
            Document doc = new Document("_id", new ObjectId()) //nroMembro
            .append("nome", sindicato.getNome());
            
            // insere a informação no banco
            sindicatos.insertOne(doc);
        } catch (MongoException e) {
            throw new InsertException("Sindicato");
        }
    }

    public void delete(Sindicato sindicato) throws DeleteException {
        try {
            // chama a tabela
            sindicatos = conexao.getCollection("sindicatos");

            // cria a busca utilizando o método "eq()" do "Filters"
            Bson query = eq("_id", sindicato.getNroMembro());

            // deleta a informação no banco
            sindicatos.deleteOne(query);
        } catch (MongoException e) {
            throw new DeleteException("Sindicato");
        }
    }

    public Sindicato select(ObjectId nroMembro) throws SelectException {

        try {
            // chama a tabela
            sindicatos = conexao.getCollection("sindicatos");

            // cria a busca utilizando o método "eq()" do "Filters"
            Bson query = eq("_id", nroMembro);

            // acha o documento para transformar
            Document doc = sindicatos.find(query).first();

            // transforma o documento em um sindicato
            Sindicato sindicato = new Sindicato(nroMembro, doc.getString("nome"));

            return sindicato;

        } catch (MongoException e) {
            throw new SelectException("Sindicato");
        }

    }

    public List<Sindicato> selectAll() throws SelectException {

        try {
            // chama a tabela
            sindicatos = conexao.getCollection("sindicatos");

            // faz o "select all", ou seja, retorna todos os objetos da tabela
            FindIterable<Document> docs = sindicatos.find();

            List<Sindicato> lista = new LinkedList<>();

            // Iterando sobre cada documento e transformando-os em sindicatos
            // Por fim, os adicionando na lista
            for(Document d: docs){
                Sindicato sindicato = new Sindicato(d.getObjectId("_id"), d.getString("nome"));
                lista.add(sindicato);
            }

            return lista;
        } catch (MongoException e) {
            throw new SelectException("Sindicatos");
        }

    }
}

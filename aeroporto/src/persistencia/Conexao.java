package persistencia;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;

public class Conexao {
    
    private static MongoClient mongoClient = null;
    private static MongoDatabase banco = null;

    private Conexao(){}

    public static MongoDatabase getConexao() throws MongoException {

        if( banco == null ){
            String host = "localhost";
            int porta = 27017;
            mongoClient = new MongoClient(host, porta);
            banco = mongoClient.getDatabase("aeroporto");
        }

        return banco;

    }

}

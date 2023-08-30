package excecoes;

import com.mongodb.MongoException;

public class InsertException extends MongoException {
        
    public InsertException(String tabela){
        super("Ocorreu um erro ao inserir um(a) " + tabela + "\n");
    }

}

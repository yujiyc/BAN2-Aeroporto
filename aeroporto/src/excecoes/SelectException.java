package excecoes;

import com.mongodb.MongoException;

public class SelectException extends MongoException {
    
    public SelectException(String tabela){
        super("Ocorreu um erro ao selecionar um(a) " + tabela + "\n");
    }
}

package excecoes;

import com.mongodb.MongoException;

public class UpdateException extends MongoException {
    
    public UpdateException(String tabela){
        super("Ocorreu um erro ao atualizar um(a) " + tabela + "\n");
    }
}

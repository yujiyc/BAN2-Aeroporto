package excecoes;

import com.mongodb.MongoException;

public class DeleteException extends MongoException {
    
    public DeleteException(String tabela){
        super("Ocorreu um erro ao deletar um(a) " + tabela + "\n");
    }
}

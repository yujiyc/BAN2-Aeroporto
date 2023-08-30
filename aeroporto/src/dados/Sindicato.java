package dados;

import org.bson.types.ObjectId;

public class Sindicato {
    
    private ObjectId nroMembro;
    private String nome;

    public Sindicato(){}

    public Sindicato(ObjectId nroMembro, String nome){
        this.setNroMembro(nroMembro);
        this.setNome(nome);
    }

    public void setNroMembro(ObjectId nroMembro){
        this.nroMembro = nroMembro;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public ObjectId getNroMembro(){
        return this.nroMembro;
    }

    public String getNome(){
        return this.nome;
    }
}

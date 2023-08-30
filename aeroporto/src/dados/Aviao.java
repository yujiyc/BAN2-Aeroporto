package dados;

import org.bson.types.ObjectId;

public class Aviao {
    
    private ObjectId nroRegistro;
    private String nome;
    private Modelo modelo;

    public Aviao(){}

    public Aviao(ObjectId nroRegistro, String nome, Modelo modelo) {
        this.nroRegistro = nroRegistro;
        this.nome = nome;
        this.modelo = modelo;
    }

    public ObjectId getNroRegistro() {
        return nroRegistro;
    }

    public void setNroRegistro(ObjectId nroRegistro) {
        this.nroRegistro = nroRegistro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    
}

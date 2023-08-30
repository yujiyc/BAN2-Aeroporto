package dados;

import org.bson.types.ObjectId;

public class Testagem {
    
    private ObjectId id;
    private Tecnico tecnico;
    private Aviao aviao;
    private Teste teste;

    public Testagem(){}

    public Testagem(ObjectId id, Tecnico tecnico, Aviao aviao, Teste teste) {
        this.id = id;
        this.tecnico = tecnico;
        this.aviao = aviao;
        this.teste = teste;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Aviao getAviao() {
        return aviao;
    }

    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
    }

    public Teste getTeste() {
        return teste;
    }

    public void setTeste(Teste teste) {
        this.teste = teste;
    } 
}

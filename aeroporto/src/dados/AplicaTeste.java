package dados;

import org.bson.types.ObjectId;

public class AplicaTeste {
    
    private ObjectId id;
    private Teste teste;
    private Tecnico tecnico;
    private String data;
    private double pontObtida;
    private int horasGastas;

    public AplicaTeste(){}

    public AplicaTeste(ObjectId id, Teste teste, Tecnico tecnico, String data, double pontObtida, int horasGastas) {
        this.id = id;
        this.teste = teste;
        this.tecnico = tecnico;
        this.data = data;
        this.pontObtida = pontObtida;
        this.horasGastas = horasGastas;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Teste getTeste() {
        return teste;
    }

    public void setTeste(Teste teste) {
        this.teste = teste;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getPontObtida() {
        return pontObtida;
    }

    public void setPontObtida(double pontObtida) {
        this.pontObtida = pontObtida;
    }

    public int getHorasGastas() {
        return horasGastas;
    }

    public void setHorasGastas(int horasGastas) {
        this.horasGastas = horasGastas;
    }
    
}

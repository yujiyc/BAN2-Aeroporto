package dados;

import org.bson.types.ObjectId;

public class Teste {
    
    private ObjectId idAnac;
    private String nome;
    private double pontMax;
    private Modelo modelo;

    public Teste(){}

    public Teste(ObjectId idAnac, String nome, double pontMax, Modelo modelo) {
        this.idAnac = idAnac;
        this.nome = nome;
        this.pontMax = pontMax;
        this.modelo = modelo;
    }

    public ObjectId getIdAnac() {
        return idAnac;
    }

    public void setIdAnac(ObjectId idAnac) {
        this.idAnac = idAnac;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPontMax() {
        return pontMax;
    }

    public void setPontMax(double pontMax) {
        this.pontMax = pontMax;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    
}

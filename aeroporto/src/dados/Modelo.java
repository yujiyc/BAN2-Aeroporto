package dados;

import org.bson.types.ObjectId;

public class Modelo {
    private ObjectId codigo;
    private int capacidade;
    private double peso;
    private String nome;

    public Modelo(){}

    public Modelo(ObjectId codigo, String nome, int capacidade, double peso) {
        this.codigo = codigo;
        this.nome = nome;
        this.capacidade = capacidade;
        this.peso = peso;
    }

    public ObjectId getCodigo() {
        return codigo;
    }
    public void setCodigo(ObjectId codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
}

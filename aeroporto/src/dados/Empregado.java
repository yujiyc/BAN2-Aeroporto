package dados;

import org.bson.types.ObjectId;

public class Empregado {
    
    private ObjectId matricula;
    private String nome;
    private String endereco;
    private double salario;
    private String telefone;
    private Sindicato sindicato;

    public Empregado(){}

    public Empregado(ObjectId matricula, String nome, String endereco, double salario, String telefone, Sindicato sindicato){
        this.setMatricula(matricula);
        this.setNome(nome);
        this.setEndereco(endereco);
        this.setSalario(salario);
        this.setTelefone(telefone);
        this.setSindicato(sindicato);
    }

    public ObjectId getMatricula() {
        return matricula;
    }

    public void setMatricula(ObjectId matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Sindicato getSindicato() {
        return sindicato;
    }

    public void setSindicato(Sindicato sindicato) {
        this.sindicato = sindicato;
    }

}

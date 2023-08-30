package dados;

import java.util.List;

import org.bson.types.ObjectId;

public class Tecnico extends Empregado {
    private List<Modelo> modelos;
    
    public Tecnico() {
    }
    
    public Tecnico(List<Modelo> modelos) {
        this.modelos = modelos;
    }
    
    public Tecnico(ObjectId matricula, String nome, String endereco, double salario, String telefone, Sindicato sindicato) {
        super(matricula, nome, endereco, salario, telefone, sindicato);
    }
    
    public Tecnico(ObjectId matricula, String nome, String endereco, double salario, String telefone, Sindicato sindicato,
    List<Modelo> modelos) {
        super(matricula, nome, endereco, salario, telefone, sindicato);
        this.modelos = modelos;
    }
    
    public List<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }
}

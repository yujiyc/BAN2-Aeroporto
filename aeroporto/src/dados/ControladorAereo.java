package dados;

import org.bson.types.ObjectId;

public class ControladorAereo extends Empregado{
    private String dataExame;

    public ControladorAereo() {
    }

    public ControladorAereo(ObjectId matricula, String nome, String endereco, double salario, String telefone,
            Sindicato sindicato, String data_exame) {
        super(matricula, nome, endereco, salario, telefone, sindicato);
        this.dataExame = data_exame;
    }

    public String getDataExame() {
        return dataExame;
    }

    public void setDataExame(String dataExame) {
        this.dataExame = dataExame;
    }
}

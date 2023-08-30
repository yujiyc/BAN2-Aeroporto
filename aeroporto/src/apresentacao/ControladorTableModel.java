package apresentacao;

import javax.swing.table.AbstractTableModel;

import excecoes.SelectException;
import negocio.Sistema;

public class ControladorTableModel extends AbstractTableModel {
    private Sistema sistema = Sistema.getSistema();
    private String[] colunas = { "Matricula", "Nome", "Telefone", "Salário", "Endereço", "Sindicato", "Data Exame"};

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        try {
            return sistema.buscaControladoresAereos().size();
        } catch (SelectException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                try {
                    return sistema.buscaControladoresAereos().get(linha).getMatricula();
                } catch (SelectException e3) {
                    e3.printStackTrace();
                }

            case 1:
                try {
                    return sistema.buscaControladoresAereos().get(linha).getNome();
                } catch (SelectException e2) {
                    e2.printStackTrace();
                }

            case 2:
                try {
                    return sistema.buscaControladoresAereos().get(linha).getTelefone();
                } catch (SelectException e1) {
                    e1.printStackTrace();
                }
            
            case 3:
                try {
                    return sistema.buscaControladoresAereos().get(linha).getSalario();
                } catch (SelectException e2) {
                    e2.printStackTrace();
                }

            case 4:
                try {
                    return sistema.buscaControladoresAereos().get(linha).getEndereco();
                } catch (SelectException e1) {
                    e1.printStackTrace();
                }
            
            case 5:
                try {
                    return sistema.buscaControladoresAereos().get(linha).getSindicato().getNome();
                } catch (SelectException e2) {
                    e2.printStackTrace();
                }
            
            case 6:
                try {
                    return sistema.buscaControladoresAereos().get(linha).getDataExame();
                } catch (SelectException e2) {
                    e2.printStackTrace();
                }

            default:
                break;
        }
        return null;
    }
}
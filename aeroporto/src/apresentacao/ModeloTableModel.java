package apresentacao;

import javax.swing.table.AbstractTableModel;

import excecoes.SelectException;
import negocio.Sistema;

public class ModeloTableModel extends AbstractTableModel {
    private Sistema sistema = Sistema.getSistema();
    private String[] colunas = { "Codigo", "Capacidade", "Peso", "Nome"};

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        try {
            return sistema.buscaModelos().size();
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
                    return sistema.buscaModelos().get(linha).getCodigo();
                } catch (SelectException e3) {
                    e3.printStackTrace();
                }

            case 1:
                try {
                    return sistema.buscaModelos().get(linha).getCapacidade();
                } catch (SelectException e2) {
                    e2.printStackTrace();
                }

            case 2:
                try {
                    return sistema.buscaModelos().get(linha).getPeso();
                } catch (SelectException e1) {
                    e1.printStackTrace();
                }

            case 3:
                try {
                    return sistema.buscaModelos().get(linha).getNome().toString();
                } catch (SelectException e) {
                    e.printStackTrace();
                }

            default:
                break;
        }
        return null;
    }
}
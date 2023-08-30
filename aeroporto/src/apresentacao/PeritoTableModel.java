package apresentacao;

import javax.swing.table.AbstractTableModel;

import excecoes.SelectException;
import negocio.Sistema;

public class PeritoTableModel extends AbstractTableModel {
    private Sistema sistema = Sistema.getSistema();
    private String[] colunas = { "Nro. Registro", "Nome", "Modelo"};

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        try {
            return sistema.buscaAvioes().size();
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
                    return sistema.buscaAvioes().get(linha).getNome();
                } catch (SelectException e2) {
                    e2.printStackTrace();
                }

            case 1:
                try {
                    return sistema.buscaAvioes().get(linha).getModelo().getNome();
                } catch (SelectException e1) {
                    e1.printStackTrace();
                }

            default:
                break;
        }
        return null;
    }
}
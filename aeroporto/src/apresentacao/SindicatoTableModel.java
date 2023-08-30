package apresentacao;

import javax.swing.table.AbstractTableModel;

import excecoes.SelectException;
import negocio.Sistema;

public class SindicatoTableModel extends AbstractTableModel {
    private Sistema sistema = Sistema.getSistema();
    private String[] colunas = { "Nro. Membro", "Nome"};

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        try {
            return sistema.buscaSindicatos().size();
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
                    return sistema.buscaSindicatos().get(linha).getNroMembro();
                } catch (SelectException e3) {
                    e3.printStackTrace();
                }

            case 1:
                try {
                    return sistema.buscaSindicatos().get(linha).getNome();
                } catch (SelectException e2) {
                    e2.printStackTrace();
                }

            default:
                break;
        }
        return null;
    }
}
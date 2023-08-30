package apresentacao;

import javax.swing.table.AbstractTableModel;

import excecoes.SelectException;
import negocio.Sistema;

public class AplicaTableModel extends AbstractTableModel {
    private Sistema sistema = Sistema.getSistema();
    private String[] colunas = { "Id", "Tecnico", "Teste", "Data", "Horas Gastas", "Pontuação obtida"};

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        try {
            return sistema.buscaAplicaTestes().size();
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
                    return sistema.buscaAplicaTestes().get(linha).getId();
                } catch (SelectException e3) {
                    e3.printStackTrace();
                }

            case 1:
                try {
                    return sistema.buscaAplicaTestes().get(linha).getTecnico().getNome();
                } catch (SelectException e2) {
                    e2.printStackTrace();
                }

            case 2:
                try {
                    return sistema.buscaAplicaTestes().get(linha).getTeste().getNome();
                } catch (SelectException e1) {
                    e1.printStackTrace();
                }
            
            case 3:
                try {
                    return sistema.buscaAplicaTestes().get(linha).getData();
                } catch (SelectException e2) {
                    e2.printStackTrace();
                }

            case 4:
                try {
                    return sistema.buscaAplicaTestes().get(linha).getHorasGastas();
                } catch (SelectException e1) {
                    e1.printStackTrace();
                }
            
            case 5:
                try {
                    return sistema.buscaAplicaTestes().get(linha).getPontObtida();
                } catch (SelectException e2) {
                    e2.printStackTrace();
                }

            default:
                break;
        }
        return null;
    }
}
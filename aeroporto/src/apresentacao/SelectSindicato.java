package apresentacao;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;

public class SelectSindicato extends JPanel {

    public SelectSindicato() {
        setBackground(Color.WHITE);
        JTable tabela = new JTable();
        SindicatoTableModel tableModel = new SindicatoTableModel();
        tabela.setModel(tableModel);
        tabela.setFillsViewportHeight(true);
        tabela.setSize(870, 400);
        tabela.getColumnModel().getColumn(0).setMaxWidth(120);
        tabela.getColumnModel().getColumn(1).setMaxWidth(280);
        add(tabela);
    }
}
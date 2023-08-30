package apresentacao;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;

public class SelectModelo extends JPanel {

    public SelectModelo() {
        setBackground(Color.WHITE);
        JTable tabela = new JTable();
        ModeloTableModel tableModel = new ModeloTableModel();
        tabela.setModel(tableModel);
        tabela.setFillsViewportHeight(true);
        tabela.setSize(700, 400);
        tabela.getColumnModel().getColumn(0).setMaxWidth(50);
        tabela.getColumnModel().getColumn(1).setMaxWidth(50);
        tabela.getColumnModel().getColumn(2).setMaxWidth(300);
        tabela.getColumnModel().getColumn(3).setMaxWidth(300);
        add(tabela);
    }
}
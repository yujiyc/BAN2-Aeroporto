package apresentacao;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;

public class SelectAplica extends JPanel {

    public SelectAplica() {
        setBackground(Color.WHITE);
        JTable tabela = new JTable();
        AplicaTableModel tableModel = new AplicaTableModel();
        tabela.setModel(tableModel);
        tabela.setFillsViewportHeight(true);
        tabela.setSize(1050, 400);
        tabela.getColumnModel().getColumn(0).setMaxWidth(50);
        tabela.getColumnModel().getColumn(1).setMaxWidth(300);
        tabela.getColumnModel().getColumn(2).setMaxWidth(300);
        tabela.getColumnModel().getColumn(3).setMaxWidth(300);
        tabela.getColumnModel().getColumn(4).setMaxWidth(50);
        tabela.getColumnModel().getColumn(5).setMaxWidth(50);
        add(tabela);
    }
}
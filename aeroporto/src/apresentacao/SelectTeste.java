package apresentacao;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class SelectTeste extends JPanel {

    public SelectTeste() {
        setBackground(Color.WHITE);
        JTable tabela = new JTable();
        TesteTableModel tableModel = new TesteTableModel();
        tabela.setModel(tableModel);
        tabela.setFillsViewportHeight(true);
        tabela.setSize(500, 400);
        tabela.getColumnModel().getColumn(0).setMaxWidth(120);
        tabela.getColumnModel().getColumn(1).setMaxWidth(280);
        tabela.getColumnModel().getColumn(2).setMaxWidth(50);
        tabela.getColumnModel().getColumn(3).setMaxWidth(50);
        add(tabela);
        JButton button = new JButton("Select");
        add(button);
    }
}
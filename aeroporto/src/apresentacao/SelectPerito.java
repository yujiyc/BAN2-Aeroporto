package apresentacao;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class SelectPerito extends JPanel {

    public SelectPerito() {
        setBackground(Color.WHITE);
        JTable tabela = new JTable();
        PeritoTableModel tableModel = new PeritoTableModel();
        tabela.setModel(tableModel);
        tabela.setFillsViewportHeight(true);
        tabela.setSize(870, 400);
        tabela.getColumnModel().getColumn(0).setMaxWidth(120);
        tabela.getColumnModel().getColumn(1).setMaxWidth(280);
        add(tabela);
        JButton button = new JButton("Select");
        add(button);
    }
}
package apresentacao;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

public class SelectAviao extends JPanel {

    public SelectAviao() {
        setBackground(Color.WHITE);
        JTable tabela = new JTable();
        AviaoTableModel tableModel = new AviaoTableModel();
        tabela.setModel(tableModel);
        tabela.setFillsViewportHeight(true);
        tabela.setSize(650, 400);
        tabela.getColumnModel().getColumn(0).setMaxWidth(50);
        tabela.getColumnModel().getColumn(1).setMaxWidth(300);
        tabela.getColumnModel().getColumn(2).setMaxWidth(300);
        add(tabela);
        JButton button = new JButton("Select");
        add(button);
    }
}
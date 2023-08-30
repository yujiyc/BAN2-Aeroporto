package apresentacao;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import excecoes.SelectException;

public class TelaModelo extends JFrame{
    private JTabbedPane guias = new JTabbedPane();
    
    public TelaModelo() throws SQLException, SelectException{
        setTitle("Modelo");
        setBounds(100, 100, 600, 400);
        setResizable(false);
        
        guias.addTab("Insert", new InsertModelo());
        guias.addTab("Delete", new DeleteModelo());
        guias.addTab("Select", new SelectModelo());
        add(guias);
    }
}

package apresentacao;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import excecoes.SelectException;

public class TelaControladorAereo extends JFrame{
    private JTabbedPane guias = new JTabbedPane();
    
    public TelaControladorAereo() throws SQLException, SelectException{
        setTitle("Controlador Aereo");
        setBounds(100, 100, 600, 400);
        setResizable(false);
        
        guias.addTab("Insert", new InsertControladorAereo());
        guias.addTab("Delete", new DeleteControladorAereo());
        guias.addTab("Select", new SelectControladorAereo());
        add(guias);
    }
}

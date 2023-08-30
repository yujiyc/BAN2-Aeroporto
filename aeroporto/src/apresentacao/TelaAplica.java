package apresentacao;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import excecoes.SelectException;

public class TelaAplica extends JFrame{
    private JTabbedPane guias = new JTabbedPane();
    
    public TelaAplica() throws SQLException, SelectException{
        setTitle("Aplica");
        setBounds(100, 100, 600, 400);
        setResizable(false);
        
        guias.addTab("Insert", new InsertAplica());
        guias.addTab("Delete", new DeleteAplica());
        guias.addTab("Select", new SelectAplica());
        add(guias);
    }
}

package apresentacao;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import excecoes.SelectException;

public class TelaAviao extends JFrame{
    private JTabbedPane guias = new JTabbedPane();
    
    public TelaAviao() throws SQLException, SelectException{
        setTitle("Aviao");
        setBounds(100, 100, 600, 400);
        setResizable(false);
        
        guias.addTab("Insert", new InsertAviao());
        guias.addTab("Delete", new DeleteAviao());
        guias.addTab("Select", new SelectAviao());
        add(guias);
    }
}

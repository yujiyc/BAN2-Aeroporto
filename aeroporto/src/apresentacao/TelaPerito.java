package apresentacao;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import excecoes.SelectException;

public class TelaPerito extends JFrame{
    private JTabbedPane guias = new JTabbedPane();
    
    public TelaPerito() throws SQLException, SelectException{
        setTitle("Perito em");
        setBounds(100, 100, 600, 400);
        setResizable(false);
        
        guias.addTab("Insert", new InsertPerito());
        guias.addTab("Delete", new DeletePerito());
        guias.addTab("Select", new SelectPerito());
        add(guias);
    }
}

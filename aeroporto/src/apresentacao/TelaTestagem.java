package apresentacao;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import excecoes.SelectException;

public class TelaTestagem extends JFrame{
    private JTabbedPane guias = new JTabbedPane();
    
    public TelaTestagem() throws SQLException, SelectException{
        setTitle("Testagem");
        setBounds(100, 100, 600, 400);
        setResizable(false);
        
        guias.addTab("Insert", new InsertTestagem());
        guias.addTab("Delete", new SelectModelo());
        guias.addTab("Select", new SelectTestagem());
        add(guias);
    }
}

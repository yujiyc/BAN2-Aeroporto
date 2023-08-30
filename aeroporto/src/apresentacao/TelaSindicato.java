package apresentacao;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import excecoes.SelectException;

public class TelaSindicato extends JFrame{
    private JTabbedPane guias = new JTabbedPane();
    
    public TelaSindicato() throws SQLException, SelectException{
        setTitle("Sindicato");
        setBounds(100, 100, 600, 400);
        setResizable(false);
        
        guias.addTab("Insert", new InsertSindicato());
        guias.addTab("Delete", new DeleteSindicato());
        guias.addTab("Select", new SelectSindicato());
        add(guias);
    }
}

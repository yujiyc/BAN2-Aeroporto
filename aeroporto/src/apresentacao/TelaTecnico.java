package apresentacao;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import excecoes.SelectException;

public class TelaTecnico extends JFrame{
    private JTabbedPane guias = new JTabbedPane();
    
    public TelaTecnico() throws SQLException, SelectException{
        setTitle("Tecnico");
        setBounds(100, 100, 600, 400);
        setResizable(false);
        
        guias.addTab("Insert", new InsertTecnico());
        guias.addTab("Delete", new DeleteTecnico());
        guias.addTab("Select", new SelectTecnico());
        add(guias);
    }
}

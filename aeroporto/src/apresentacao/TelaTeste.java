package apresentacao;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import excecoes.SelectException;

public class TelaTeste extends JFrame{
    private JTabbedPane guias = new JTabbedPane();
    
    public TelaTeste() throws SQLException, SelectException{
        setTitle("Teste");
        setBounds(100, 100, 600, 400);
        setResizable(false);
        
        guias.addTab("Insert", new InsertTeste());
        guias.addTab("Delete", new DeleteTeste());
        guias.addTab("Select", new SelectTeste());
        add(guias);
    }
}

package apresentacao;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import excecoes.SelectException;

public class TelaEmpregado extends JFrame{
    private JTabbedPane guias = new JTabbedPane();
    
    public TelaEmpregado() throws SQLException, SelectException{
        setTitle("Empregado");
        setBounds(100, 100, 600, 400);
        setResizable(false);
        
        guias.addTab("Insert", new InsertEmpregado());
        guias.addTab("Delete", new DeleteEmpregado());
        guias.addTab("Select", new SelectEmpregado());
        add(guias);
    }
}

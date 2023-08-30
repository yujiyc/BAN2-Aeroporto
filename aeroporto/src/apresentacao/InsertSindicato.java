package apresentacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dados.*;
import excecoes.InsertException;
import excecoes.SelectException;
import negocio.*;

public class InsertSindicato extends JPanel {
    private Sistema sistema = Sistema.getSistema();

    public InsertSindicato() throws SQLException, SelectException {
        setBackground(Color.WHITE);

        JLabel informe_nome = new JLabel("Informe o nome:");
        informe_nome.setHorizontalAlignment(JLabel.CENTER);
        add(informe_nome);
        JTextField texto_nome = new JTextField();
        texto_nome.setColumns(10);
        add(texto_nome);

        JButton button = new JButton("INSERIR");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Sindicato sindicato = new Sindicato();
                sindicato.setNome(texto_nome.getText());
                try {
                    sistema.insereSindicato(sindicato);
                } catch (SelectException e1) {
                    e1.printStackTrace();
                } catch (InsertException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
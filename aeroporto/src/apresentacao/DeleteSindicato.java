package apresentacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dados.Sindicato;
import excecoes.DeleteException;
import excecoes.SelectException;
import negocio.Sistema;

public class DeleteSindicato extends JPanel {
    private Sistema sistema = Sistema.getSistema();
    int index = 0;

    public DeleteSindicato() throws SQLException, SelectException {
        setBackground(Color.WHITE);
        
        List<String> lista_sindicatos = new LinkedList<String>();
        List<Sindicato> sindicatos = sistema.buscaSindicatos();
        for (Sindicato m:sindicatos){
            lista_sindicatos.add(m.getNome());
        }
        String[] array_sindicatos = new String[lista_sindicatos.size()];
        int i = 0;
        for(String s:lista_sindicatos){
            array_sindicatos[i] = s;
            i++;
        }

        JLabel informe_sindicato = new JLabel("Selecione o sindicato:");
        informe_sindicato.setHorizontalAlignment(JLabel.CENTER);
        add(informe_sindicato);
        JComboBox<String> comboBox = new JComboBox<String>(array_sindicatos);
        comboBox.setBounds(50, 50, 200, 25);
        add(comboBox);

        JButton button = new JButton("DELETAR");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int j=0;j<lista_sindicatos.size();j++){
                    if(array_sindicatos[j] == comboBox.getSelectedItem().toString()){
                        index = j;
                    }
                }
                try {
                    sistema.deletaSindicato(sindicatos.get(index));
                } catch (DeleteException e1) {
                    e1.printStackTrace();
                }
            }            
        });
    }
}
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

import dados.Modelo;
import excecoes.DeleteException;
import excecoes.SelectException;
import negocio.Sistema;

public class DeleteModelo extends JPanel {
    private Sistema sistema = Sistema.getSistema();
    int index = 0;

    public DeleteModelo() throws SQLException, SelectException {
        setBackground(Color.WHITE);
        
        List<String> lista_modelos = new LinkedList<String>();
        List<Modelo> modelos = sistema.buscaModelos();
        for (Modelo m:modelos){
            lista_modelos.add(m.getNome());
        }
        String[] array_modelos = new String[lista_modelos.size()];
        int i = 0;
        for(String s:lista_modelos){
            array_modelos[i] = s;
            i++;
        }

        JLabel informe_modelo = new JLabel("Selecione o modelo:");
        informe_modelo.setHorizontalAlignment(JLabel.CENTER);
        add(informe_modelo);
        JComboBox<String> comboBox = new JComboBox<String>(array_modelos);
        comboBox.setBounds(50, 50, 200, 25);
        add(comboBox);

        JButton button = new JButton("DELETAR");
        add(button);
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int j=0;j<lista_modelos.size();j++){
                    if(array_modelos[j] == comboBox.getSelectedItem().toString()){
                        index = j;
                    }
                }
                try {
                    sistema.deletaModelo(modelos.get(index));
                } catch (DeleteException e1) {
                    e1.printStackTrace();
                }
            }            
        });
    }
}
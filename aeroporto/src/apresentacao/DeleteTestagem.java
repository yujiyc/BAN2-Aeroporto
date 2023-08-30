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

import dados.Testagem;
import excecoes.DeleteException;
import excecoes.SelectException;
import negocio.Sistema;

public class DeleteTestagem extends JPanel {
    private Sistema sistema = Sistema.getSistema();
    int index = 0;

    public DeleteTestagem() throws SQLException, SelectException {
        setBackground(Color.WHITE);
        
        List<String> lista_testagens = new LinkedList<String>(); //TODO: Talvez a atualizar a lista de long para String
        List<Testagem> testagens = sistema.buscaTestagens();
        for (Testagem m:testagens){
            lista_testagens.add(m.getId().toString());
        }
        String[] array_testagens = new String[lista_testagens.size()];
        int i = 0;
        for(String s:lista_testagens){
            array_testagens[i] = s;
            i++;
        }

        JLabel informe_testagem = new JLabel("Selecione a testagem:");
        informe_testagem.setHorizontalAlignment(JLabel.CENTER);
        add(informe_testagem);
        JComboBox<String> comboBox = new JComboBox<String>(array_testagens);
        comboBox.setBounds(50, 50, 200, 25);
        add(comboBox);

        JButton button = new JButton("DELETAR");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int j=0;j<lista_testagens.size();j++){
                    if(array_testagens[j].toString() == comboBox.getSelectedItem().toString()){
                        index = j;
                    }
                }
                try {
                    sistema.deletaTestagem(testagens.get(index));
                } catch (DeleteException e1) {
                    e1.printStackTrace();
                }
            }            
        });
    }
}
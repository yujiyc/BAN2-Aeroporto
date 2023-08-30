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

import dados.Teste;
import excecoes.DeleteException;
import excecoes.SelectException;
import negocio.Sistema;

public class DeleteTeste extends JPanel {
    private Sistema sistema = Sistema.getSistema();
    int index = 0;

    public DeleteTeste() throws SQLException, SelectException {
        setBackground(Color.WHITE);
        
        List<String> lista_testes = new LinkedList<String>();
        List<Teste> testes = sistema.buscaTestes();
        for (Teste m:testes){
            lista_testes.add(m.getNome());
        }
        String[] array_testes = new String[lista_testes.size()];
        int i = 0;
        for(String s:lista_testes){
            array_testes[i] = s;
            i++;
        }

        JLabel informe_teste = new JLabel("Selecione o teste:");
        informe_teste.setHorizontalAlignment(JLabel.CENTER);
        add(informe_teste);
        JComboBox<String> comboBox = new JComboBox<String>(array_testes);
        comboBox.setBounds(50, 50, 200, 25);
        add(comboBox);

        JButton button = new JButton("DELETAR");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int j=0;j<lista_testes.size();j++){
                    if(array_testes[j] == comboBox.getSelectedItem().toString()){
                        index = j;
                    }
                }
                try {
                    sistema.deletaTeste(testes.get(index));
                } catch (DeleteException e1) {
                    e1.printStackTrace();
                }
            }            
        });
    }
}
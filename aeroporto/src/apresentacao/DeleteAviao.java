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

import dados.Aviao;
import excecoes.DeleteException;
import excecoes.SelectException;
import negocio.Sistema;

public class DeleteAviao extends JPanel {
    private Sistema sistema = Sistema.getSistema();
    int index=0;

    public DeleteAviao() throws SQLException, SelectException {
        setBackground(Color.WHITE);
        
        List<String> lista_avioes = new LinkedList<String>();
        List<Aviao> avioes = sistema.buscaAvioes();
        for (Aviao m:avioes){
            lista_avioes.add(m.getNome());
        }
        String[] array_avioes = new String[lista_avioes.size()];
        int i = 0;
        for(String s:lista_avioes){
            array_avioes[i] = s;
            i++;
        }

        JLabel informe_aviao = new JLabel("Selecione o aviao:");
        informe_aviao.setHorizontalAlignment(JLabel.CENTER);
        add(informe_aviao);
        JComboBox<String> comboBox = new JComboBox<String>(array_avioes);
        comboBox.setBounds(50, 50, 200, 25);
        add(comboBox);

        JButton button = new JButton("DELETAR");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int j=0;j<lista_avioes.size();j++){
                    if(array_avioes[j] == comboBox.getSelectedItem().toString()){
                        index = j;
                    }
                }
                try {
                    sistema.deletaAviao(avioes.get(index));
                } catch (DeleteException e1) {
                    e1.printStackTrace();
                }
            }            
        });
    }
}
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

import dados.AplicaTeste;
import excecoes.DeleteException;
import excecoes.SelectException;
import negocio.Sistema;

public class DeleteAplica extends JPanel {
    private Sistema sistema = Sistema.getSistema();

    public DeleteAplica() throws SQLException, SelectException {
        setBackground(Color.WHITE);
        
        List<String> lista_aplicacoes = new LinkedList<String>(); //TODO: Talvez a atualizar a lista de long para String
        List<AplicaTeste> aplicacoes = sistema.buscaAplicaTestes();
        for (AplicaTeste m:aplicacoes){
            lista_aplicacoes.add(m.getId().toString());
        }
        String[] array_aplicacoes = new String[lista_aplicacoes.size()];
        int i = 0;
        for(String s:lista_aplicacoes){
            array_aplicacoes[i] = s;
            i++;
        }

        JLabel informe_aplica = new JLabel("Selecione a aplicação:");
        informe_aplica.setHorizontalAlignment(JLabel.CENTER);
        add(informe_aplica);
        JComboBox<String> comboBox = new JComboBox<String>(array_aplicacoes);
        comboBox.setBounds(50, 50, 200, 25);
        add(comboBox);

        JButton button = new JButton("DELETAR");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // COMO ESCOLHER O AVIAO?
                AplicaTeste aplica = new AplicaTeste();
                try {
                    //TODO: Talvez a atualizar a lista de long para String
                    //Pego a posição selecionada no comboBox e passo como parametro para a lista de aplicações
                    // Assim eu consigo pegar o objeto e pegar o seu ObjectId
                    aplica = sistema.buscaAplicaTeste((aplicacoes.get(comboBox.getSelectedIndex()).getId()));
                    sistema.deletaAplicaTeste(aplica);
                } catch (SelectException e1) {
                    e1.printStackTrace();
                } catch (DeleteException e1) {
                    e1.printStackTrace();
                }
            }            
        });
    }
}
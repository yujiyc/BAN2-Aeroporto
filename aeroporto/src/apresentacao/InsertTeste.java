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
import javax.swing.JTextField;

import dados.*;
import excecoes.InsertException;
import excecoes.SelectException;
import negocio.*;

public class InsertTeste extends JPanel {
    private Sistema sistema = Sistema.getSistema();
    int index = 0;

    public InsertTeste() throws SQLException, SelectException {
        setBackground(Color.WHITE);

        JLabel informe_nome = new JLabel("Informe o nome:");
        informe_nome.setHorizontalAlignment(JLabel.CENTER);
        add(informe_nome);
        JTextField texto_nome = new JTextField();
        texto_nome.setColumns(10);
        add(texto_nome);

        JLabel informe_pont = new JLabel("Informe a pontuaçao máxima:");
        informe_pont.setHorizontalAlignment(JLabel.CENTER);
        add(informe_pont);
        JTextField texto_pont = new JTextField();
        texto_pont.setColumns(10);
        add(texto_pont);
        
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

        JButton button = new JButton("INSERIR");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Teste teste = new Teste();
                teste.setNome(texto_nome.getText());
                teste.setPontMax(Double.parseDouble(texto_pont.getText()));
                for(int j=0;j<lista_modelos.size();j++){
                    if(array_modelos[j] == comboBox.getSelectedItem().toString()){
                        index = j;
                    }
                }
                teste.setModelo(modelos.get(index));
                try {
                    sistema.insereTeste(teste);
                } catch (SelectException e1) {
                    e1.printStackTrace();
                } catch (InsertException e1) {
                    e1.printStackTrace();
                }
            }
            
        });
    }
}
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

public class InsertTecnico extends JPanel {
    private Sistema sistema = Sistema.getSistema();
    int index = 0;

    public InsertTecnico() throws SQLException, SelectException {
        setBackground(Color.WHITE);

        List<String> lista_sindicatos = new LinkedList<String>();
        List<Sindicato> sindicatos = sistema.buscaSindicatos();
        for (Sindicato s:sindicatos){
            lista_sindicatos.add(s.getNome());
        }
        String[] array_sindicatos = new String[lista_sindicatos.size()];
        int i = 0;
        for(String s:lista_sindicatos){
            array_sindicatos[i] = s;
            i++;
        }

        JLabel informe_nome = new JLabel("Informe o nome:");
        informe_nome.setHorizontalAlignment(JLabel.CENTER);
        add(informe_nome);
        JTextField texto_nome = new JTextField();
        texto_nome.setColumns(10);
        add(texto_nome);

        JLabel informe_fone = new JLabel("Informe o telefone:");
        informe_fone.setHorizontalAlignment(JLabel.CENTER);
        add(informe_fone);
        JTextField texto_fone = new JTextField();
        texto_fone.setColumns(10);
        add(texto_fone);

        JLabel informe_salario = new JLabel("Informe o salario:");
        informe_salario.setHorizontalAlignment(JLabel.CENTER);
        add(informe_salario);
        JTextField texto_salario = new JTextField();
        texto_salario.setColumns(10);
        add(texto_salario);

        JLabel informe_endereco = new JLabel("Informe o endereco:");
        informe_endereco.setHorizontalAlignment(JLabel.CENTER);
        add(informe_endereco);
        JTextField texto_endereco = new JTextField();
        texto_endereco.setColumns(10);
        add(texto_endereco);
        
        JLabel informe_sindicato = new JLabel("Selecione o sindicato:");
        informe_sindicato.setHorizontalAlignment(JLabel.CENTER);
        add(informe_sindicato);
        JComboBox<String> comboBox = new JComboBox<String>(array_sindicatos);
        comboBox.setBounds(50, 50, 200, 25);
        add(comboBox);

        JButton button = new JButton("INSERIR");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Tecnico tecnico = new Tecnico();
                tecnico.setNome(texto_nome.getText());
                tecnico.setTelefone(texto_endereco.getText());
                tecnico.setSalario(Double.parseDouble(texto_salario.getText()));
                tecnico.setEndereco(texto_endereco.getText());
                for(int j=0;j<lista_sindicatos.size();j++){
                    if(array_sindicatos[j] == comboBox.getSelectedItem().toString()){
                        index = j;
                    }
                }
                tecnico.setSindicato(sindicatos.get(index));
                
                try {
                    sistema.insereEmpregado(tecnico);
                } catch (SelectException e1) {
                    e1.printStackTrace();
                } catch (InsertException e1) {
                    e1.printStackTrace();
                }
            }
            
        });
    }
}
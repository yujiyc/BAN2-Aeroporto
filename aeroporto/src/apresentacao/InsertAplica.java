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

import dados.AplicaTeste;
import dados.Tecnico;
import dados.Teste;
import excecoes.InsertException;
import excecoes.SelectException;
import negocio.*;

public class InsertAplica extends JPanel {
    private Sistema sistema = Sistema.getSistema();
    int index = 0;

    public InsertAplica() throws SQLException, SelectException {
        setBackground(Color.WHITE);

        List<String> lista_tecnicos = new LinkedList<String>();
        List<Tecnico> tecnicos = sistema.buscaTecnicos();
        for (Tecnico t:tecnicos){
            lista_tecnicos.add(t.getNome());
        }
        String[] array_tecnicos = new String[lista_tecnicos.size()];
        int i = 0;
        for(String s:lista_tecnicos){
            array_tecnicos[i] = s;
            i++;
        }
        
        JLabel informe_tecnico = new JLabel("Selecione o tecnico:");
        informe_tecnico.setHorizontalAlignment(JLabel.CENTER);
        add(informe_tecnico);
        JComboBox<String> comboBox_tecnico = new JComboBox<String>(array_tecnicos);
        comboBox_tecnico.setBounds(50, 50, 200, 25);
        add(comboBox_tecnico);

        List<String> lista_testes = new LinkedList<String>();
        List<Teste> testes = sistema.buscaTestes();
        for (Teste t:testes){
            lista_testes.add(t.getNome());
        }
        String[] array_testes = new String[lista_testes.size()];
        i = 0;
        for(String s:lista_testes){
            array_testes[i] = s;
            i++;
        }
        
        JLabel informe_teste = new JLabel("Selecione o teste:");
        informe_teste.setHorizontalAlignment(JLabel.CENTER);
        add(informe_teste);
        JComboBox<String> comboBox_teste = new JComboBox<String>(array_testes);
        comboBox_teste.setBounds(50, 50, 200, 25);
        add(comboBox_teste);

        JLabel informe_data = new JLabel("Informe a data:");
        informe_data.setHorizontalAlignment(JLabel.CENTER);
        add(informe_data);
        JTextField texto_data = new JTextField();
        texto_data.setColumns(10);
        add(texto_data);

        JLabel informe_hora = new JLabel("Informe as horas gastas:");
        informe_hora.setHorizontalAlignment(JLabel.CENTER);
        add(informe_hora);
        JTextField texto_hora = new JTextField();
        texto_hora.setColumns(10);
        add(texto_hora);

        JLabel informe_pont = new JLabel("Informe a pontuação obtida:");
        informe_pont.setHorizontalAlignment(JLabel.CENTER);
        add(informe_pont);
        JTextField texto_pont = new JTextField();
        texto_pont.setColumns(10);
        add(texto_pont);

        JButton button = new JButton("INSERIR");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                AplicaTeste aplica = new AplicaTeste();
                Tecnico tecnico = new Tecnico();
                for(int j=0;j<lista_tecnicos.size();j++){
                    if(array_tecnicos[j] == comboBox_tecnico.getSelectedItem().toString()){
                        index = j;
                    }
                }
                tecnico = tecnicos.get(index);
                
                Teste teste = new Teste();
                for(int j=0;j<lista_testes.size();j++){
                    if(array_testes[j] == comboBox_teste.getSelectedItem().toString()){
                        index = j;
                    }
                }
                teste = testes.get(index);

                aplica.setTecnico(tecnico);
                aplica.setTeste(teste);
                aplica.setPontObtida(Double.parseDouble(texto_pont.getText()));
                aplica.setData(texto_data.getText());
                aplica.setHorasGastas(Integer.parseInt(texto_hora.getText()));
                
                try {
                    sistema.insereAplicaTeste(aplica);
                } catch (SelectException e1) {
                    e1.printStackTrace();
                } catch (InsertException e1) {
                    e1.printStackTrace();
                }
            }
            
        });
    }
}
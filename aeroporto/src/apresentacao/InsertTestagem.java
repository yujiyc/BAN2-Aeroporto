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
import dados.Tecnico;
import dados.Testagem;
import dados.Teste;
import excecoes.InsertException;
import excecoes.SelectException;
import negocio.*;

public class InsertTestagem extends JPanel {
    private Sistema sistema = Sistema.getSistema();
    int index = 0;

    public InsertTestagem() throws SQLException, SelectException {
        setBackground(Color.WHITE);

        List<String> lista_avioes = new LinkedList<String>();
        List<Aviao> avioes = sistema.buscaAvioes();
        for (Aviao a:avioes){
            lista_avioes.add(a.getNome());
        }
        String[] array_avioes = new String[lista_avioes.size()];
        int i = 0;
        for(String s:lista_avioes){
            array_avioes[i] = s;
            i++;
        }
        
        JLabel informe_modelo = new JLabel("Selecione o aviao:");
        informe_modelo.setHorizontalAlignment(JLabel.CENTER);
        add(informe_modelo);
        JComboBox<String> comboBox_aviao = new JComboBox<String>(array_avioes);
        comboBox_aviao.setBounds(50, 50, 200, 25);
        add(comboBox_aviao);

        List<String> lista_tecnicos = new LinkedList<String>();
        List<Tecnico> tecnicos = sistema.buscaTecnicos();
        for (Tecnico t:tecnicos){
            lista_tecnicos.add(t.getNome());
        }
        String[] array_tecnicos = new String[lista_tecnicos.size()];
        i = 0;
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

        JButton button = new JButton("INSERIR");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int j=0;j<lista_avioes.size();j++){
                    if(array_avioes[j] == comboBox_aviao.getSelectedItem().toString()){
                        index = j;
                    }
                }
                Testagem testagem = new Testagem();
                testagem.setAviao(avioes.get(index));
                
                for(int j=0;j<lista_tecnicos.size();j++){
                    if(array_tecnicos[j] == comboBox_tecnico.getSelectedItem().toString()){
                        index = j;
                    }
                }
                testagem.setTecnico(tecnicos.get(index));

                for(int j=0;j<lista_testes.size();j++){
                    if(array_testes[j] == comboBox_teste.getSelectedItem().toString()){
                        index = j;
                    }
                }
                testagem.setTeste(testes.get(index));
                
                try {
                    sistema.insereTestagem(testagem);
                } catch (SelectException e1) {
                    e1.printStackTrace();
                } catch (InsertException e1) {
                    e1.printStackTrace();
                }
            }
            
        });
    }
}
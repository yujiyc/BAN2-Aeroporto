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
import dados.Tecnico;
import excecoes.InsertException;
import excecoes.SelectException;
import negocio.*;

public class InsertPerito extends JPanel {
    private Sistema sistema = Sistema.getSistema();
    int index = 0;

    public InsertPerito() throws SQLException, SelectException {
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

        List<String> lista_modelos = new LinkedList<String>();
        List<Modelo> modelos = sistema.buscaModelos();
        for (Modelo m:modelos){
            lista_modelos.add(m.getNome());
        }
        String[] array_modelos = new String[lista_modelos.size()];
        i = 0;
        for(String s:lista_modelos){
            array_modelos[i] = s;
            i++;
        }

        JLabel informe_modelo = new JLabel("Selecione o modelo:");
        informe_modelo.setHorizontalAlignment(JLabel.CENTER);
        add(informe_modelo);
        JComboBox<String> comboBox_modelo = new JComboBox<String>(array_modelos);
        comboBox_modelo.setBounds(50, 50, 200, 25);
        add(comboBox_modelo);

        JButton button = new JButton("INSERIR");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Modelo modelo = new Modelo();
                for(int j=0;j<lista_modelos.size();j++){
                    if(array_modelos[j] == comboBox_modelo.getSelectedItem().toString()){
                        index = j;
                    }
                }
                modelo = modelos.get(index);
                
                Tecnico tecnico = new Tecnico();
                for(int j=0;j<lista_tecnicos.size();j++){
                    if(array_tecnicos[j] == comboBox_tecnico.getSelectedItem().toString()){
                        index = j;
                    }
                }
                tecnico = tecnicos.get(index);
                
                try {
                    sistema.insereModeloTecnico(tecnico, modelo);
                } catch (SelectException e1) {
                    e1.printStackTrace();
                } catch (InsertException e1) {
                    e1.printStackTrace();
                }
            }
            
        });
    }
}
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
// import dados.Perito;
import excecoes.DeleteException;
import excecoes.SelectException;
import negocio.Sistema;

public class DeletePerito extends JPanel {
    private Sistema sistema = Sistema.getSistema();
    int index = 0;

    public DeletePerito() throws SQLException, SelectException {
        setBackground(Color.WHITE);
        
        List<String> lista_tecnicos = new LinkedList<String>();
        List<Tecnico> tecnicos = sistema.buscaTecnicos();
        for (Tecnico m:tecnicos){
            lista_tecnicos.add(m.getNome());
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
        JComboBox<String> comboBox = new JComboBox<String>(array_tecnicos);
        comboBox.setBounds(50, 50, 200, 25);
        add(comboBox);

        JButton button = new JButton("MOSTRAR MODELOS");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int j=0;j<lista_tecnicos.size();j++){
                    if(array_tecnicos[j] == comboBox.getSelectedItem().toString()){
                        index = j;
                    }
                }
                String[] array_modelos = new String[tecnicos.get(index).getModelos().size()];
                List<Modelo> modelos = tecnicos.get(index).getModelos();
                Tecnico atual = tecnicos.get(index);
                List<String> lista_modelos = new LinkedList<String>();
                for (Modelo m:modelos){
                    lista_modelos.add(m.getNome());
                }
                int i = 0;
                for(String s:lista_modelos){
                    array_modelos[i] = s;
                    i++;
                }

                JLabel informe_modelo = new JLabel("Selecione o modelo:");
                informe_modelo.setHorizontalAlignment(JLabel.CENTER);
                add(informe_modelo);
                JComboBox<String> comboBox_dois = new JComboBox<String>(array_modelos);
                comboBox_dois.setBounds(50, 50, 200, 25);
                add(comboBox_dois);

                JButton button_delet = new JButton("DELETAR");
                add(button_delet);

                button.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        for(int j=0;j<lista_modelos.size();j++){
                            if(array_modelos[j] == comboBox_dois.getSelectedItem().toString()){
                                index = j;
                            }
                        }
                        try {
                            sistema.deleteModeloTecnico(atual, modelos.get(index));
                        } catch (DeleteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }            
        });
    }
}
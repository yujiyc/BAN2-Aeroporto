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

import dados.ControladorAereo;
import excecoes.DeleteException;
import excecoes.SelectException;
import negocio.Sistema;

public class DeleteControladorAereo extends JPanel {
    private Sistema sistema = Sistema.getSistema();
    int index = 0;

    public DeleteControladorAereo() throws SQLException, SelectException {
        setBackground(Color.WHITE);
        
        List<String> lista_controladores = new LinkedList<String>();
        List<ControladorAereo> controladores = sistema.buscaControladoresAereos();
        for (ControladorAereo m:controladores){
            lista_controladores.add(m.getNome());
        }
        String[] array_controladores = new String[lista_controladores.size()];
        int i = 0;
        for(String s:lista_controladores){
            array_controladores[i] = s;
            i++;
        }

        JLabel informe_controlador = new JLabel("Selecione o controlador aereo:");
        informe_controlador.setHorizontalAlignment(JLabel.CENTER);
        add(informe_controlador);
        JComboBox<String> comboBox = new JComboBox<String>(array_controladores);
        comboBox.setBounds(50, 50, 200, 25);
        add(comboBox);

        JButton button = new JButton("DELETAR");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int j=0;j<lista_controladores.size();j++){
                    if(array_controladores[j] == comboBox.getSelectedItem().toString()){
                        index = j;
                    }
                }
                try {
                    sistema.deletaEmpregado(controladores.get(index));
                } catch (DeleteException e1) {
                    e1.printStackTrace();
                }
            }            
        });
    }
}
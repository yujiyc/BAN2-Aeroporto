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

import dados.Empregado;
import excecoes.DeleteException;
import excecoes.SelectException;
import negocio.Sistema;

public class DeleteEmpregado extends JPanel {
    private Sistema sistema = Sistema.getSistema();
    int index = 0;

    public DeleteEmpregado() throws SQLException, SelectException {
        setBackground(Color.WHITE);
        
        List<String> lista_empregados = new LinkedList<String>();
        List<Empregado> empregados = sistema.buscaEmpregados();
        for (Empregado m:empregados){
            lista_empregados.add(m.getNome());
        }
        String[] array_empregados = new String[lista_empregados.size()];
        int i = 0;
        for(String s:lista_empregados){
            array_empregados[i] = s;
            i++;
        }

        JLabel informe_empregado = new JLabel("Selecione o empregado:");
        informe_empregado.setHorizontalAlignment(JLabel.CENTER);
        add(informe_empregado);
        JComboBox<String> comboBox = new JComboBox<String>(array_empregados);
        comboBox.setBounds(50, 50, 200, 25);
        add(comboBox);

        JButton button = new JButton("DELETAR");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int j=0;j<lista_empregados.size();j++){
                    if(array_empregados[j] == comboBox.getSelectedItem().toString()){
                        index = j;
                    }
                }
                try {
                    sistema.deletaEmpregado(empregados.get(index));
                } catch (DeleteException e1) {
                    e1.printStackTrace();
                }
            }            
        });
    }
}
package apresentacao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dados.Modelo;
import excecoes.InsertException;
import excecoes.SelectException;
import negocio.*;

public class InsertModelo extends JPanel {
    private Sistema sistema = Sistema.getSistema();

    public InsertModelo() {
        setBackground(Color.WHITE);

        JLabel informe_nome = new JLabel("Informe o nome:");
        informe_nome.setHorizontalAlignment(JLabel.CENTER);
        add(informe_nome);
        JTextField texto_nome = new JTextField();
        texto_nome.setColumns(10);
        add(texto_nome);

        JLabel informe_capacidade = new JLabel("Informe a capacidade:");
        informe_capacidade.setHorizontalAlignment(JLabel.CENTER);
        add(informe_capacidade);
        JTextField texto_capacidade = new JTextField();
        texto_capacidade.setColumns(10);
        add(texto_capacidade);

        JLabel informe_peso = new JLabel("Informe o peso:");
        informe_peso.setHorizontalAlignment(JLabel.CENTER);
        add(informe_peso);
        JTextField texto_peso = new JTextField();
        texto_peso.setColumns(10);
        add(texto_peso);

        JButton button = new JButton("INSERIR");
        add(button);

        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Modelo modelo = new Modelo();
                modelo.setNome(texto_nome.getText());
                modelo.setCapacidade(Integer.parseInt(texto_capacidade.getText()));
                modelo.setPeso(Double.parseDouble(texto_peso.getText()));
                
                try {
                    sistema.insereModelo(modelo);
                } catch (SelectException e1) {
                    e1.printStackTrace();
                } catch (InsertException e1) {
                    e1.printStackTrace();
                }
            }
            
        });
    }
}
package apresentacao;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import excecoes.SelectException;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Tela extends JFrame {
    private JPanel painel = new JPanel();
    private JComboBox<Menu> comboBox = new JComboBox<Menu>(Menu.values());
    
    public Tela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setResizable(false);
        setTitle("Sistema de Aeroporto");
        setContentPane(painel);
        painel.setLayout(null);
        painel.setBackground(Color.WHITE);

        // ImageIcon foto = new ImageIcon(getClass().getResource("imdb.png"));
        // JLabel label = new JLabel(foto);
        // label.setBounds(178,10,213,50);
        // add(label);

        Menu();
    }

    public void Menu(){
        JPanel painelMenu = new JPanel();
        painelMenu.setLayout(null);
        painelMenu.setSize(300, 150);
        painelMenu.setLocation(150, 100);
        painelMenu.setBackground(Color.WHITE);
        painelMenu.setBorder(javax.swing.BorderFactory.createTitledBorder("Escolha a Tabela"));
        add(painelMenu);

        // JLabel texto = new JLabel("Escolha a tabela que deseja fazer alterções");
        // texto.setBounds(0,20,150,100);
        // texto.setHorizontalAlignment(JLabel.CENTER);
        // // texto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // painelMenu.add(texto);

        comboBox.setBounds(50, 50, 200, 25);
        painelMenu.add(comboBox);

        JButton botaoGerador = new JButton("CONFIRMAR");
        botaoGerador.setBounds(50, 85, 200, 22);
        painelMenu.add(botaoGerador);

        botaoGerador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Menu.MODELO == (Menu) comboBox.getSelectedItem()) {
                    TelaModelo telaModelo;
                    try {
                        telaModelo = new TelaModelo();
                        telaModelo.setVisible(true);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (SelectException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Menu.AVIAO == (Menu) comboBox.getSelectedItem()) {
                    TelaAviao telaAviao;
                    try {
                        telaAviao = new TelaAviao();
                        telaAviao.setVisible(true);
                    } catch (SQLException | SelectException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Menu.TESTE == (Menu) comboBox.getSelectedItem()) {
                    TelaTeste telaTeste;
                    try {
                        telaTeste = new TelaTeste();
                        telaTeste.setVisible(true);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (SelectException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Menu.SINDICATO == (Menu) comboBox.getSelectedItem()) {
                    TelaSindicato telaSindicato;
                    try {
                        telaSindicato = new TelaSindicato();
                        telaSindicato.setVisible(true);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (SelectException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Menu.EMPREGADO == (Menu) comboBox.getSelectedItem()) {
                    TelaEmpregado telaEmpregado;
                    try {
                        telaEmpregado = new TelaEmpregado();
                        telaEmpregado.setVisible(true);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (SelectException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Menu.TECNICO == (Menu) comboBox.getSelectedItem()) {
                    TelaTecnico telaTecnico;
                    try {
                        telaTecnico = new TelaTecnico();
                        telaTecnico.setVisible(true);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (SelectException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Menu.CONTROLADOR_AEREO == (Menu) comboBox.getSelectedItem()) {
                    TelaControladorAereo telaControladorAereo;
                    try {
                        telaControladorAereo = new TelaControladorAereo();
                        telaControladorAereo.setVisible(true);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (SelectException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Menu.TESTAGEM == (Menu) comboBox.getSelectedItem()) {
                    TelaTestagem telaTestagem;
                    try {
                        telaTestagem = new TelaTestagem();
                        telaTestagem.setVisible(true);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (SelectException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Menu.PERITO_EM == (Menu) comboBox.getSelectedItem()) {
                    TelaPerito telaPerito;
                    try {
                        telaPerito = new TelaPerito();
                        telaPerito.setVisible(true);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (SelectException e1) {
                        e1.printStackTrace();
                    }
                }

                if (Menu.APLICA == (Menu) comboBox.getSelectedItem()) {
                    TelaAplica telaAplica;
                    try {
                        telaAplica = new TelaAplica();
                        telaAplica.setVisible(true);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (SelectException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}

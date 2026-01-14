/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.view.UserInterface;

import br.ufjf.dcc025.trabalho2.controller.RegisterController;
import br.ufjf.dcc025.trabalho2.controller.SecretaryController;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidRegisterException;
import br.ufjf.dcc025.trabalho2.model.users.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public abstract class UserPanel<T extends User> extends JPanel {
    
    protected final MainFrame mainPage;
    private final JButton logOutBtn;
    protected final JTabbedPane tabbedPane;
    private final User user;
            
    public UserPanel(MainFrame main, User user){
        this.mainPage = main;
        this.user = user;
        setLayout(new BorderLayout());
        
        JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlHeader.setBackground(new Color(60, 100, 180)); 
        JLabel lblCentral = new JLabel("Central do Usuário. Bem vindo "+ ((mainPage.getUser() != null) ? this.mainPage.getUser().getName() : "") + "!");
        
        lblCentral.setForeground(Color.WHITE);
        lblCentral.setFont(new Font("Arial", Font.BOLD, 14));
        pnlHeader.add(lblCentral);
        
        logOutBtn = new JButton("LogOut");
        logOutBtn.addActionListener(this::logOutBtnActionPerformed);
        pnlHeader.add(logOutBtn);
        
        add(pnlHeader, BorderLayout.NORTH);
        
        tabbedPane = new JTabbedPane();
        
    }
    protected JPanel createPersonalDataTab(){   //fiz esse método pra poder adicionar 
        JPanel painel = initDataComponents();   //o que o médico tiver de diferente na página de dados
                                                
        return painel;
    }
    
    
    protected JPanel initDataComponents(){        //Inicializa todos os campos na página de dados pessoais
        JPanel painel = new JPanel();
        painel.setLayout(new GridBagLayout());
        
        GridBagConstraints centralizator = new GridBagConstraints();
        centralizator.insets = new Insets(10, 10, 10, 10);
        
        JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitle = new JLabel("Dados do "+this.mainPage.getUser().getProfile().toString());
        pnlHeader.add(lblTitle);
        centralizator.gridwidth = 4;
        centralizator.gridx = 0;
        centralizator.gridy = 0;
        centralizator.anchor = GridBagConstraints.NORTH;
        painel.add(pnlHeader, centralizator);
        
        JLabel lblName = new JLabel("Nome: ");
        centralizator.gridwidth = 1;
        centralizator.gridx = 0;
        centralizator.gridy = 1;
        centralizator.anchor = GridBagConstraints.LINE_START;
        painel.add(lblName, centralizator);
        
        JTextField nameField = new JTextField(15);
        nameField.setText(this.mainPage.getUser().getName());
        centralizator.gridwidth = 1;
        centralizator.gridx = 1;
        centralizator.gridy = 1;
        centralizator.anchor = GridBagConstraints.LINE_END;
        painel.add(nameField, centralizator);
        
        JLabel lblEmail = new JLabel("E-mail: ");
        centralizator.gridwidth = 1;
        centralizator.gridx = 0;
        centralizator.gridy = 2;
        centralizator.anchor = GridBagConstraints.LINE_START;
        painel.add(lblEmail, centralizator);
        
        JTextField emailField = new JTextField(15);
        emailField.setText(this.mainPage.getUser().getEmail());
        centralizator.gridwidth = 1;
        centralizator.gridx = 1;
        centralizator.gridy = 2;
        centralizator.anchor = GridBagConstraints.LINE_END;
        painel.add(emailField, centralizator);
        
        JLabel lblPassword = new JLabel("Senha: ");
        centralizator.gridwidth = 1;
        centralizator.gridx = 0;
        centralizator.gridy = 3;
        centralizator.anchor = GridBagConstraints.LINE_START;
        painel.add(lblPassword, centralizator);
        
        JTextField passwordField = new JTextField(15);
        passwordField.setText(this.mainPage.getUser().getPassword());
        centralizator.gridwidth = 1;
        centralizator.gridx = 1;
        centralizator.gridy = 3;
        centralizator.anchor = GridBagConstraints.LINE_END;
        painel.add(passwordField, centralizator);
        
        JLabel lblCpf = new JLabel("CPF: ");
        centralizator.gridwidth = 1;
        centralizator.gridx = 0;
        centralizator.gridy = 4;
        centralizator.anchor = GridBagConstraints.LINE_START;
        painel.add(lblCpf, centralizator);
        
        JTextField cpfField = new JTextField(15);
        cpfField.setText(this.mainPage.getUser().getCPF().toString());
        cpfField.setEditable(false);
        centralizator.gridwidth = 1;
        centralizator.gridx = 1;
        centralizator.gridy = 4;
        centralizator.anchor = GridBagConstraints.LINE_END;
        painel.add(cpfField, centralizator);
        
        JLabel lblPhone = new JLabel("Telefone: ");
        centralizator.gridwidth = 1;
        centralizator.gridx = 0;
        centralizator.gridy = 5;
        centralizator.anchor = GridBagConstraints.LINE_START;
        painel.add(lblPhone, centralizator);
        
        JTextField phoneField = new JTextField(15);
        phoneField.setText(this.mainPage.getUser().getphoneNumber().toString());
        centralizator.gridwidth = 1;
        centralizator.gridx = 1;
        centralizator.gridy = 5;
        centralizator.anchor = GridBagConstraints.LINE_END;
        painel.add(phoneField, centralizator);
        
        JButton confirmBtn = new JButton("Confirmar");
        confirmBtn.addActionListener((ActionEvent evt) -> {
            //Registra um novo usuário e deleta a versão antiga
            RegisterController nova = new RegisterController();
            SecretaryController secret = new SecretaryController();
            User backup = user;
            try{
                secret.removeUserByCPF(cpfField.getText());
                nova.registerUser(nameField.getText(), emailField.getText(), cpfField.getText(), phoneField.getText(), passwordField.getText(), user.getProfile(), user.getStatus());
            }catch(InvalidRegisterException e){
                JOptionPane.showMessageDialog(null, "Erro no cadastro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                nova.registerUser(backup.getName(), backup.getEmail(), backup.getCPF().toString(), backup.getphoneNumber().toString(), backup.getPassword(), backup.getProfile(), backup.getStatus());
            }
        });
        
        centralizator.gridwidth = 2;
        centralizator.gridx = 0;
        centralizator.gridy = 6;
        centralizator.anchor = GridBagConstraints.SOUTH;
        painel.add(confirmBtn, centralizator);
        
        return painel;
    }
    
    private void logOutBtnActionPerformed(ActionEvent evt){
        this.mainPage.changeScreen("login");
    }
}

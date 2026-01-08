package br.ufjf.dcc025.trabalho2.view.UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.ufjf.dcc025.trabalho2.controller.LoginController;
import br.ufjf.dcc025.trabalho2.model.error.InvalidLoginException;

public class LoginPanel extends JPanel {
    
    private static JTextField campoUser;
    private static JPasswordField senhaUser;
    private static JButton enterBtn;
    private static JLabel userLabel;
    private static JLabel passwordLabel;
    private static JLabel invalidCredLbl;
    private MainFrame mainFrame;
    
    public LoginPanel(MainFrame main){
        
        this.mainFrame = main;
        setLayout(new GridBagLayout());
        
        GridBagConstraints centralizator = new GridBagConstraints();
        centralizator.insets = new Insets(10, 10, 10, 10);
        
        userLabel = new JLabel("Usuário: ");
        centralizator.gridwidth = 1;
        centralizator.gridx = 0;
        centralizator.gridy = 1;
        centralizator.anchor = GridBagConstraints.LINE_START;
        add(userLabel, centralizator);
        
        campoUser = new JTextField(15);
        centralizator.gridx = 1;
        centralizator.gridy = 1;
        centralizator.anchor = GridBagConstraints.LINE_END;
        add(campoUser, centralizator);
        
        passwordLabel = new JLabel("Senha: ");
        centralizator.gridx = 0;
        centralizator.gridy = 2;
        centralizator.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, centralizator);
        
        senhaUser = new JPasswordField(15);
        centralizator.gridx = 1;
        centralizator.gridy = 2;
        centralizator.anchor = GridBagConstraints.LINE_START;
        add(senhaUser, centralizator);
        
        enterBtn = new JButton("Entrar");
        enterBtn.addActionListener(this::enterBtnActionPerformed);
        enterBtn.setPreferredSize(new Dimension(100, 30));
        centralizator.gridx = 0;
        centralizator.gridy = 3;
        centralizator.gridwidth = 2;
        centralizator.anchor = GridBagConstraints.CENTER;
        add(enterBtn, centralizator);
        
        invalidCredLbl = new JLabel("*Usuário e/ou senha inválido!");
        centralizator.gridx = 0;
        centralizator.gridy = 4;
        centralizator.anchor = GridBagConstraints.CENTER;
        add(invalidCredLbl, centralizator);
        invalidCredLbl.setForeground(Color.RED);
        invalidCredLbl.setVisible(false);
        
        campoUser.requestFocusInWindow();
    }
    
    private void limpaCampo(javax.swing.JTextField c){
        c.setText("");
    }
    
    private void enterBtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if(campoUser.getText().equals("Usuario01") && senhaUser.getText().equals("senha"))
        {
            invalidCredLbl.setVisible(false);
            limpaCampo(campoUser);
            limpaCampo(senhaUser);
            this.mainFrame.changeScreen("secretary");
        }
        else
        {
            try {
                new LoginController().Login(campoUser.getText(), senhaUser.getText());
                this.mainFrame.changeScreen("secretary");
            } 
            catch (InvalidLoginException e) {
                invalidCredLbl.setVisible(true);
                limpaCampo(campoUser);
                limpaCampo(senhaUser);
            }
            limpaCampo(campoUser);
            limpaCampo(senhaUser);
        }
    }
}


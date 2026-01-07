package br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels;

import javax.swing.*;

public class RegisterFrame {
    private static JFrame janelaPrincipal;
    private static JPanel Credentials;
    
    public RegisterFrame(){
        janelaPrincipal = new JFrame();
        janelaPrincipal.setSize(640, 400);
        
        Credentials = new RegisterPanel();
        janelaPrincipal.add(Credentials);
        janelaPrincipal.setContentPane(Credentials);
        
        janelaPrincipal.setLocationRelativeTo(null);
        Credentials.setVisible(true);
        janelaPrincipal.setVisible(true);  
        janelaPrincipal.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
}

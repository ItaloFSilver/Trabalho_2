package br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RegisterFrame extends JFrame {
    private static JPanel Credentials;
    
    public RegisterFrame(){
        
        setSize(640, 400);
        
        Credentials = new RegisterPanel(this);
        add(Credentials);
        setContentPane(Credentials);
        
        setLocationRelativeTo(null);
        Credentials.setVisible(true);
        setVisible(true);  
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
}

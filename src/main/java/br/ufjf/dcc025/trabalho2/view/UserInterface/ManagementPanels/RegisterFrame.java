/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class RegisterFrame extends JFrame {
    private static JPanel Credentials;
    
    public RegisterFrame(DefaultTableModel main, int index){
        
        setSize(640, 400);
        
        Credentials = new RegisterPanel(this, main, index);
        add(Credentials);
        setContentPane(Credentials);
        
        setLocationRelativeTo(null);
        Credentials.setVisible(true);
        setVisible(true);  
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
}

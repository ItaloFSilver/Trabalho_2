package br.ufjf.dcc025.trabalho2.view.UserInterface;

import br.ufjf.dcc025.trabalho2.model.users.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class UserPanel extends JPanel {
    
    protected final MainFrame mainPage;
    private final JButton logOutBtn;
    protected final JTabbedPane tabbedPane;
    protected User user;
            
    public UserPanel(MainFrame main){
        this.mainPage = main;
        setLayout(new BorderLayout());
        
        JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlHeader.setBackground(new Color(60, 100, 180)); 
        JLabel lblCentral = new JLabel("Central do Usuário. Bem vindo!");
        
        lblCentral.setForeground(Color.WHITE);
        lblCentral.setFont(new Font("Arial", Font.BOLD, 14));
        pnlHeader.add(lblCentral);
        
        logOutBtn = new JButton("LogOut");
        logOutBtn.addActionListener(this::logOutBtnActionPerformed);
        pnlHeader.add(logOutBtn);
        
        add(pnlHeader, BorderLayout.NORTH);
        
        tabbedPane = new JTabbedPane();
        
    }
    private void logOutBtnActionPerformed(ActionEvent evt){
        this.mainPage.changeScreen("login");
    }
}

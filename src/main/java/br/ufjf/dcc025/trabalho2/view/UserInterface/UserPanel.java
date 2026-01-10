package br.ufjf.dcc025.trabalho2.view.UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class UserPanel extends JPanel {
    
    private final MainFrame mainPage;
    private final JButton logOutBtn;
    private final JTabbedPane tabbedPane;
    
    public UserPanel(MainFrame main){
        this.mainPage = main;
        
        JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlHeader.setBackground(new Color(60, 100, 180)); 
        JLabel lblBemVindo = new JLabel("Olá, " + ". Bem-vindo ao sistema!");
        lblBemVindo.setForeground(Color.WHITE);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 14));
        pnlHeader.add(lblBemVindo);
        
        logOutBtn = new JButton("LogOut");
        logOutBtn.addActionListener(this::logOutBtnActionPerformed);
        pnlHeader.add(logOutBtn);
        
        add(pnlHeader, BorderLayout.NORTH);
        
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
    }
    private void logOutBtnActionPerformed(ActionEvent evt){
        this.mainPage.changeScreen("login");
    }
}

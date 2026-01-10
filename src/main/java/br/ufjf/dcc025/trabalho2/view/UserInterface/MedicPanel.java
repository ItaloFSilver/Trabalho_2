package br.ufjf.dcc025.trabalho2.view.UserInterface;

import br.ufjf.dcc025.trabalho2.model.users.User;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class MedicPanel extends UserPanel {
    
    public MedicPanel(MainFrame main) {
        super(main);
        
        this.tabbedPane.addTab("Agendamentos", new JPanel(new BorderLayout()));
        this.tabbedPane.addTab("Dados Pessoais", createPersonalDataTab());
        
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    @Override
    public JPanel createPersonalDataTab(){
        JPanel jPanel = initDataComponents();
        
        GridBagConstraints centralizator = new GridBagConstraints();
        centralizator.insets = new Insets(10, 10, 10, 10);
        
        JToggleButton active = new JToggleButton();
        if (this.mainPage.getUser().getStatus())
            active.setText(" Ativo ");
        else
            active.setText("Ausente");
            
        centralizator.gridwidth = 2;
        centralizator.gridx = 2;
        centralizator.gridy = 0;
        centralizator.anchor = centralizator.anchor = GridBagConstraints.NORTH;
        jPanel.add(active, centralizator);
        
        active.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                mainPage.getUser().toggleActive();
                if(active.getText().equals(" Ativo "))
                    active.setText("Ausente");
                else
                    active.setText(" Ativo ");
            }
        });
        
        return jPanel;
    }
    
    
    
}

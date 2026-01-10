
package br.ufjf.dcc025.trabalho2.view.UserInterface;

import br.ufjf.dcc025.trabalho2.controller.AppointmentController;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import br.ufjf.dcc025.trabalho2.model.users.User;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class PatientPanel extends UserPanel {
            
    private AppointmentController consultController;
    private List<Appointment> agenda;
    private DefaultTableModel appoint;
    
    public PatientPanel(MainFrame main) {
        super(main);
        
        this.tabbedPane.addTab("Agendamentos", createAppointmentPage());
        this.tabbedPane.addTab("Exames", new JPanel(new BorderLayout()));
        this.tabbedPane.addTab("Dados Pessoais", new JPanel(new BorderLayout()));
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    
    private JPanel createAppointmentPage(){
        JPanel appPanel = new JPanel(new BorderLayout());
        
        consultController = new AppointmentController();
        
        
        String[] colunas = {"Data", "Médico", "Status"};
        
        appoint = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(appoint);
        
        
        
        JScrollPane scroll = new JScrollPane(tabela);
        appPanel.add(scroll, BorderLayout.CENTER);
        
        
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JButton updateBtn = new JButton("Atualizar");
        updateBtn.addActionListener(this::updateBtnActionListener);
        
        toolbar.add(updateBtn);
        
        appPanel.add(toolbar, BorderLayout.NORTH);
        
        return appPanel;
    }
    
    private void updateBtnActionListener(java.awt.event.ActionEvent evt){
        this.user = this.mainPage.getUser();
        if(agenda == null){
            agenda = consultController.listThis(user.getCPF().toString());
            for(Appointment a : agenda){
                String [] data = {a.getDate(), a.getMedicName(), a.getCheck()};
                System.out.println(a.getMedicName());
                appoint.addRow(data);
            }
        }
    }
}

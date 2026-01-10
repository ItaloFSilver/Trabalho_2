package br.ufjf.dcc025.trabalho2.view.UserInterface;

import br.ufjf.dcc025.trabalho2.controller.AppointmentController;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import br.ufjf.dcc025.trabalho2.model.users.User;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MedicPanel extends UserPanel {
    private AppointmentController consultController;
    private DefaultTableModel appoint;
    private List<Appointment> agenda;
    
    public MedicPanel(MainFrame main) {
        super(main);
        
        this.tabbedPane.addTab("Agendamentos", createAppointmentPage());
        this.tabbedPane.addTab("Dados Pessoais", createPersonalDataTab());
        
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    @Override
    public JPanel createPersonalDataTab(){
        JPanel jPanel = initDataComponents();
        
        GridBagConstraints centralizator = new GridBagConstraints();
        centralizator.insets = new Insets(10, 10, 10, 10);
        
        JToggleButton active = new JToggleButton();
        active.setText(" Ativo ");
            
            
        centralizator.gridwidth = 2;
        centralizator.gridx = 2;
        centralizator.gridy = 0;
        centralizator.anchor = centralizator.anchor = GridBagConstraints.NORTH;
        jPanel.add(active, centralizator);
        
        active.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            if(active.getText().equals(" Ativo "))
                active.setText("Ausente");
            else
                active.setText(" Ativo ");
            }
        });
        
        return jPanel;
    }
    
    private JPanel createAppointmentPage(){ //cria uma tabela e puxa o user armazenado pela mainPage pra poder ver as consultas

        JPanel appPanel = new JPanel(new BorderLayout());
        
        consultController = new AppointmentController();
        
        String[] colunas = {"Data", "Paciente", "Status"};
        
        appoint = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
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
    private void updateBtnActionListener(java.awt.event.ActionEvent evt){   //fiz esse botão pra atualizar a tabela, mas tá meio bugado
        this.user = this.mainPage.getUser();    //puxa o User armazenado na main pra acessar os dados
        if(agenda == null){
            agenda = consultController.listThis(user.getCPF().toString());  //pesquisa pelo cpf do usuário pra achar certin
            for(Appointment a : agenda){
                String [] data = {a.getDate(), a.getPatientName(), a.getCheck()};   
                System.out.println(a.getMedicName());
                appoint.addRow(data);
            }
        }
    }
    
    
}

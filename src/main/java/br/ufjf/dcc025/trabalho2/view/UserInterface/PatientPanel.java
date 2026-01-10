
package br.ufjf.dcc025.trabalho2.view.UserInterface;

import br.ufjf.dcc025.trabalho2.controller.AppointmentController;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import br.ufjf.dcc025.trabalho2.model.users.User;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class PatientPanel extends UserPanel { //resolvi padronizar os dois painés, oq vai mudar é cada subpagina
            
    private AppointmentController consultController;
    private List<Appointment> agenda;
    private DefaultTableModel appoint;
    
    public PatientPanel(MainFrame main) {
        super(main);
        
        this.tabbedPane.addTab("Agendamentos", createAppointmentPage());  //por enquanto só tem essa funcionando
        this.tabbedPane.addTab("Exames", new JPanel(new BorderLayout()));
        this.tabbedPane.addTab("Dados Pessoais", createPersonalDataTab());
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    
    private JPanel createAppointmentPage(){ //cria uma tabela e puxa o user armazenado pela mainPage pra poder ver as consultas
        JPanel appPanel = new JPanel(new BorderLayout());
        
        consultController = new AppointmentController();
        
        
        String[] colunas = {"Data", "Médico", "Status"};
        
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
                    //Vou precisar de ajuda pra acertar essa função aqui dps
    private void updateBtnActionListener(java.awt.event.ActionEvent evt){   //fiz esse botão pra atualizar a tabela, mas tá meio bugado
        this.user = this.mainPage.getUser();    //puxa o User armazenado na main pra acessar os dados
        if(agenda == null){
            agenda = consultController.listThis(user.getCPF().toString());  //pesquisa pelo cpf do usuário pra achar certin
            for(Appointment a : agenda){
                String [] data = {a.getDate(), a.getMedicName(), a.getCheck()};   
                System.out.println(a.getMedicName());
                appoint.addRow(data);
            }
        }
    }
}


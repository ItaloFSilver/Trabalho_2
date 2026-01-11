
package br.ufjf.dcc025.trabalho2.view.UserInterface;

import br.ufjf.dcc025.trabalho2.controller.AppointmentController;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import br.ufjf.dcc025.trabalho2.model.users.Patient;
import br.ufjf.dcc025.trabalho2.model.users.User;
import br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels.EditAppointmentDialog;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class PatientPanel extends UserPanel<Patient> { //resolvi padronizar os dois painés, oq vai mudar é cada subpagina
            
    private AppointmentController consultController;
    private List<Appointment> agenda;
    private DefaultTableModel appoint;
    private Patient user;
    
    public PatientPanel(MainFrame main, Patient user) {
        super(main, user);
        this.user = user;
        this.tabbedPane.addTab("Agendamentos", createAppointmentPage());  
        this.tabbedPane.addTab("Exames e atestados", new JPanel(new BorderLayout()));
        this.tabbedPane.addTab("Dados Pessoais", createPersonalDataTab()); //por enquanto só tem essa funcionando
        
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
        
        JButton editBtn = new JButton("Editar");
        editBtn.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha == -1) return;

            // 1. Pega os dados da linha selecionada
            String nomeMedico = (String) tabela.getValueAt(linha, 1); // ex: Coluna 1 é médico
            String dataAtual = (String) tabela.getValueAt(linha, 0);  // ex: Coluna 2 é data

            // 2. BUSCA HORÁRIOS DISPONÍVEIS (Isso viria do seu Controller)
            // Exemplo simulado:
            //List<String> horariosLivres = controller.getHorariosDisponiveis(nomeMedico);
    
                // Se o controller ainda não existir, simule assim para testar:
            List<String> horariosLivres = List.of("12/01/2026 14:00", "12/01/2026 15:30", "13/01/2026 09:00");

                // 3. Abre a Janela passando a lista
            EditAppointmentDialog dialog = new EditAppointmentDialog(mainPage, nomeMedico, dataAtual, horariosLivres);
            dialog.setVisible(true);

            // 4. Processa o resultado
            if (dialog.isDesmarcou()) {
                agenda = consultController.listThis(user.getCPF().toString());  //pesquisa pelo cpf do usuário pra achar certin
                for(Appointment a : agenda){  
                    if(a.getDate().equals(appoint.getValueAt(linha, 0))){
                        consultController.removeAppointment(a);
                        break;
                    }
                }
                
                appoint.removeRow(linha);
        
            } else if (dialog.isSalvou()) {
                String novaData = dialog.getNovoHorario();
        
            // Atualiza no Backend
                //controller.atualizarConsulta(linha, novaData);
        
            //  Atualiza na Tabela
                appoint.setValueAt(novaData, linha, 0);
    }
        });
        
        toolbar.add(updateBtn);
        toolbar.add(editBtn);
        
        appPanel.add(toolbar, BorderLayout.NORTH);
        
        return appPanel;
    }
                    //Vou precisar de ajuda pra acertar essa função aqui dps
    private void updateBtnActionListener(java.awt.event.ActionEvent evt){   //fiz esse botão pra atualizar a tabela, mas tá meio bugado
        if(agenda == null){
            agenda = consultController.listThis(user.getCPF().toString());  //pesquisa pelo cpf do usuário pra achar certin
            for(Appointment a : agenda){
                String [] data = {a.getDate(), a.getMedicName(), a.getCheck()};   
                System.out.println(a.getMedicName());
                appoint.addRow(data);
            }
        }
    }
    
    
    public void hideWindow(JFrame frame){
        frame.setVisible(false);
    }
}


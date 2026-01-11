
package br.ufjf.dcc025.trabalho2.view.UserInterface;

import br.ufjf.dcc025.trabalho2.controller.AppointmentController;
import br.ufjf.dcc025.trabalho2.controller.DocumentController;
import br.ufjf.dcc025.trabalho2.controller.MedicController;
import br.ufjf.dcc025.trabalho2.controller.SecretaryController;
import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import br.ufjf.dcc025.trabalho2.model.services.MedicalDocument;
import br.ufjf.dcc025.trabalho2.model.services.WorkShift;
import br.ufjf.dcc025.trabalho2.model.users.Patient;
import br.ufjf.dcc025.trabalho2.model.users.User;
import br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels.EditAppointmentDialog;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class PatientPanel extends UserPanel<Patient> { //resolvi padronizar os dois painés, oq vai mudar é cada subpagina
            
    private AppointmentController consultController;
    private List<Appointment> agenda;
    private DefaultTableModel appoint;
    private Patient user;
    private List<MedicalDocument> listaMeusDocs;
    private DocumentController documentController;
    
    public PatientPanel(MainFrame main, Patient user) {
        super(main, user);
        this.user = user;
        this.tabbedPane.addTab("Agendamentos", createAppointmentPage());  
        this.tabbedPane.addTab("Checar Disp. Visitas", createPatientsList());
        this.tabbedPane.addTab("Exames e atestados", createDocumentPanel());
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
        editBtn.addActionListener(e -> {                ///////////////////
            int linha = tabela.getSelectedRow();        ////////////////// Aqui precisamos pegar as horas que o médico tem livre pra atender
            if (linha == -1) return;
            
            String nomeMedico = (String) tabela.getValueAt(linha, 1);
            String dataAtual = (String) tabela.getValueAt(linha, 0);
            
            CPF cpf;
            Appointment alpha = null;
            List<Appointment> consult = consultController.listAll();
            for(Appointment a : consult)
                if(a.getMedicName().contains(nomeMedico.substring(0, 4)) && a.getDate().equals(dataAtual))
                    alpha = a;
            
            List<String> horariosLivres = new ArrayList<>();
            cpf = alpha.getMedicCPF();
            MedicController medic = new MedicController();
            List<WorkShift> daysOfWork = medic.loadWorkShift(cpf);
            horariosLivres = daysOfWork.get(alpha.getDayOfWeek()).getFreeTime();
             // ex: Coluna 1 é médico
              // ex: Coluna 0 é data

            // 2. BUSCA HORÁRIOS DISPONÍVEIS (Isso viria do seu Controller)
            // Exemplo simulado:
            //List<String> horariosLivres = controller.getHorariosDisponiveis(nomeMedico);
    
                // Se o controller ainda não existir, simule assim para testar:
            

                // 3. Abre a Janela passando a lista
            EditAppointmentDialog dialog = new EditAppointmentDialog(mainPage, nomeMedico, alpha.getDate().substring(0, 9), horariosLivres);
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
        if(!(agenda == null)){
            agenda.clear();
            appoint.setRowCount(0);
        }
        agenda = consultController.listThis(user.getCPF().toString());  //pesquisa pelo cpf do usuário pra achar certin
        for(Appointment a : agenda){
            String [] data = {a.getDate(), a.getMedicName(), a.getCheck()};   
            System.out.println(a.getMedicName());
            appoint.addRow(data);
        }
        
    }
    
    private JPanel createPatientsList(){        //procura por outros pacientes para verificar horário de visita
        JPanel painel = new JPanel();
        SecretaryController repo = new SecretaryController();
        List<User> patients = repo.listPatients();
        
        DefaultTableModel model;
        
        String[] colunas = {"Paciente", "Status"};
        
        model = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable tabela = new JTable(model);
        
        for(User u : patients)
            if(!(u.getCPF().equals(user.getCPF()))){
                String[] linha = {u.getName(), (u.getStatus()) ? "Visita liberada" : "Não disponível"};
                model.addRow(linha);
            }
        
        JScrollPane scroll = new JScrollPane(tabela);
        painel.add(scroll, BorderLayout.CENTER);
       
        
        return painel;
    }
    
    public void hideWindow(JFrame frame){
        frame.setVisible(false);
    }
    
    public JPanel createDocumentPanel(){
        JPanel painel = new JPanel(new BorderLayout());
        JTable tabelaDocument;
        DefaultTableModel tabelaModel;

        documentController = new DocumentController();

        String[] colunas = {"Data", "Tipo", "Médico", "Diagnóstico (Resumo)"};
        tabelaModel = new DefaultTableModel(colunas, 0) {
            @Override // Impede edição das células
            public boolean isCellEditable(int row, int column) { return false; }
        };
        
        tabelaDocument = new JTable(tabelaModel);
        
        tabelaDocument.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Clique duplo
                    mostrarDetalhes(tabelaDocument.getSelectedRow());
                }
            }
        });

        carregarDados(tabelaModel);

        painel.add(new JScrollPane(tabelaDocument), BorderLayout.CENTER);
        painel.add(new JLabel("Dica: Clique duas vezes na linha para ver os detalhes."), BorderLayout.SOUTH);
    
        return painel;
    }
    
    private void carregarDados(DefaultTableModel model) {
        listaMeusDocs = documentController.buscarPorCPF(user.getCPF());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        model.setRowCount(0); // Limpa tabela
        for (MedicalDocument doc : listaMeusDocs) {
            Object[] linha = {
                sdf.format(doc.getDataEmissao()),
                doc.getTipo(),
                doc.getDoctorCpf(),
                doc.getDiagnostico()
            };
            model.addRow(linha);
        }
    }
    

    private void mostrarDetalhes(int linha) {
        if (linha == -1) return;

        MedicalDocument doc = listaMeusDocs.get(linha);

        String mensagem = "--- " + doc.getTipo().toUpperCase() + " ---\n\n" +
                          "Data: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(doc.getDataEmissao()) + "\n" +
                          "Médico: " + doc.getDoctorCpf() + "\n\n" +
                          "DIAGNÓSTICO:\n" + doc.getDiagnostico() + "\n\n" +
                          "RECOMENDAÇÃO/RECEITA:\n" + doc.getRecomendacao();

        
        JTextArea textArea = new JTextArea(mensagem);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setSize(300, 200);

        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Detalhes do Documento", JOptionPane.INFORMATION_MESSAGE);
    } 
}




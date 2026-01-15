/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.view.UserInterface;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.ufjf.dcc025.trabalho2.controller.AppointmentController;
import br.ufjf.dcc025.trabalho2.controller.DocumentController;
import br.ufjf.dcc025.trabalho2.controller.MedicController;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidDateException;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import br.ufjf.dcc025.trabalho2.model.services.MedicalDocument;
import br.ufjf.dcc025.trabalho2.model.services.WorkShift;
import br.ufjf.dcc025.trabalho2.model.users.Medic;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;



public class MedicPanel extends UserPanel<Medic> {
    
    private AppointmentController consultController;
    private DefaultTableModel appoint;
    private List<Appointment> agenda;
    private DefaultListModel<String> listModel;
    private JList<String> listaVisual;
    private JComboBox comboDias;
    private JSpinner spinnerInicio;
    private JSpinner spinnerFim;
    private Medic user;
    
    public MedicPanel(MainFrame main, Medic user) {
        super(main, user);
        this.user = user;
        this.tabbedPane.addTab("Agendamentos", createAppointmentPage());
        this.tabbedPane.addTab("Gerenciar Agenda", DoctorSchedulePanel());
        this.tabbedPane.addTab("Emitir atestado / exame", createDoctorIssuePanel());
        this.tabbedPane.addTab("Dados Pessoais", createPersonalDataTab());
        
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    //gera a página contendo os dados pessoais do médico
    @Override
    public JPanel createPersonalDataTab(){
        JPanel jPanel = initDataComponents();
        
        GridBagConstraints centralizator = new GridBagConstraints();
        centralizator.insets = new Insets(10, 10, 10, 10);
        
        return jPanel;
    }
    
    //cria o painel com a tabela de agendamentos do médico, informando a data agendada, o paciente e se a consulta está confirmada ou não
    private JPanel createAppointmentPage(){ 

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
        
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { 
                    checkFrequency(tabela.getSelectedRow());
                }
            }
        });
        
        toolbar.add(updateBtn);
        
        appPanel.add(toolbar, BorderLayout.NORTH);
        
        return appPanel;
    }
    
    private void checkFrequency(int line){
        AppointmentController controllerAppointment = new AppointmentController();
        
        JFrame janela = new JFrame();
        JPanel frequencyDialog = new JPanel();
        janela.setSize(200, 200);
        
        JLabel questionLabel = new JLabel("Paciente compareceu?");
        
        JCheckBox confirm = new JCheckBox("Sim");
        JCheckBox reject = new JCheckBox("Não");
        JButton confirmBtn = new JButton("Confirmar");
        
        GridBagConstraints centralizator = new GridBagConstraints();
        centralizator.insets = new Insets(10, 10, 10, 10);
        
        centralizator.gridx = 0;
        centralizator.gridy = 0;
        centralizator.anchor = GridBagConstraints.NORTH;
        frequencyDialog.add(questionLabel, centralizator);
        
        centralizator.gridx = 0;
        centralizator.gridy = 1;
        centralizator.anchor = GridBagConstraints.CENTER;
        frequencyDialog.add(confirm, centralizator);
        
        centralizator.gridx = 1;
        centralizator.gridy = 1;
        centralizator.anchor = GridBagConstraints.CENTER;
        frequencyDialog.add(reject, centralizator);
        
        centralizator.gridx = 0;
        centralizator.gridy = 2;
        centralizator.anchor = GridBagConstraints.SOUTH;
        frequencyDialog.add(confirmBtn, centralizator);
        
        janela.add(frequencyDialog);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
        frequencyDialog.setVisible(true);
        
        confirmBtn.addActionListener(e-> {
            if(confirm.isSelected()){
                controllerAppointment.setAppointmentCheck(agenda.get(line), true);
            }
            if(reject.isSelected()){
                controllerAppointment.setAppointmentCheck(agenda.get(line), false);
            }
            janela.setVisible(false);
        });
    }
    //Define a ação do botão de atualizar agenda: puxa a lista do repositório e exibe a versão mais atual dos agendamentos
    private void updateBtnActionListener(java.awt.event.ActionEvent evt){   
            //puxa o User armazenado na main pra acessar os dados
        appoint.setRowCount(0);
            agenda = consultController.listThis(user.getCPF().toString());  //pesquisa pelo cpf do usuário pra achar certin
            for(Appointment a : agenda){
                String [] data = {a.getDate(), a.getPatientName(), a.getCheck()};   
                System.out.println(a.getMedicName());
                appoint.addRow(data);
            }
        
    }
    
    //cria o Painel da agenda do médico, para que ele possa registrar seus dias de serviço
    public JPanel DoctorSchedulePanel() {
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        
        JPanel panelForm = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelForm.setBorder(BorderFactory.createTitledBorder("Configurar Disponibilidade"));

        
        String[] diasSemana = {"Segunda-feira", "Terca-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sabado", "Domingo"};
        comboDias = new JComboBox<>(diasSemana);

        spinnerInicio = createHourSpinner();
        spinnerFim = createHourSpinner();

        JButton btnAdicionar = new JButton("Adicionar Horário");
        btnAdicionar.addActionListener(e -> addItemInList());

        panelForm.add(new JLabel("Dia:"));
        panelForm.add(comboDias);
        panelForm.add(new JLabel("Das:"));
        panelForm.add(spinnerInicio);
        panelForm.add(new JLabel("Até:"));
        panelForm.add(spinnerFim);
        panelForm.add(btnAdicionar);

        painel.add(panelForm, BorderLayout.NORTH);

        
        listModel = new DefaultListModel<>();
        List<WorkShift> list = user.getDisponibilityAsList();
        // Carrega o que o médico JÁ TINHA salvo anteriormente
        if (user.getDisponibilityAsList() != null) {
            for (WorkShift h : user.getDisponibilityAsList()) {
                listModel.addElement(h.toString());
            }
        }

        listaVisual = new JList<>(listModel);
        painel.add(new JScrollPane(listaVisual), BorderLayout.CENTER);

        
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton btnRemover = new JButton("Remover Selecionado");

        btnRemover.addActionListener(e -> {
            MedicController medic = new MedicController();
            List<WorkShift> listUpd = user.getDisponibilityAsList();
            int index = listaVisual.getSelectedIndex();
            if (index != -1) {
                medic.removesWorkShift(listUpd.get(index));
                listModel.remove(index);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um horário na lista para remover.");
            }
        });
        
        panelForm.add(btnRemover);

        painel.add(panelBotoes, BorderLayout.SOUTH);
        
        return painel;
    }

    //retorna a data formatada corretamente
    public String getHour(int Hours, int Minutes){        
        String hour = "";
        String minute = "";
       
        if(Hours < 10)
            hour += "0";
        hour += "" + (Hours);
        
        if(Minutes < 10)
            minute += "0";
        minute += "" + (Minutes);
        
        String fullDate = hour + ":" + minute;
        
        return fullDate;
    }

    //função para gerar o Spinner de seleção de horário
    private JSpinner createHourSpinner() {
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(editor);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 8);
        cal.set(Calendar.MINUTE, 0);
        spinner.setValue(cal.getTime());
        return spinner;
    }

    //Adiciona o horário criado pelo médico em sua agenda
    //update: Os horários aparecem ordenadamente seguindo o enum dos dias da semana
    private void addItemInList() {
        MedicController medic = new MedicController();
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        String a = spinnerInicio.getValue().toString().substring(11, 16);
        String b = spinnerFim.getValue().toString().substring(11, 16);
        System.out.println(a);
        System.out.println(b);
        try{
            medic.savesWorkShift(comboDias.getSelectedItem().toString(), parser.parse(a), parser.parse(b), user);
        }catch(ParseException exc){}
        catch(InvalidDateException invD){
            JOptionPane.showMessageDialog(null, invD);
            return;
        }
        String dia = comboDias.getSelectedItem().toString();

        if (listModel.contains(dia)) {
            JOptionPane.showMessageDialog(this, "Este horário já foi adicionado!");
            return;
        }
        listModel.clear();
        for(WorkShift w : user.getDisponibilityAsList())
            listModel.addElement(w.getDayOfWeek().toString()+" "+w.getStart()+"-"+w.getEnd());
    }
    
    //Cria o Painel de emissão de documentos(Atestado/Receitas/Exames) do médico
    public JPanel createDoctorIssuePanel() {
        JPanel medicPanel = new JPanel(new BorderLayout());
        JComboBox<String> comboTipo;
        JTextArea txtDiagnostico;
        JTextArea txtRecomendacao;
        JTextField patientCPF = criarCampoFormatado("###.###.###-##");

        medicPanel.setBorder(BorderFactory.createTitledBorder("Emitir Documento Médico"));

        
        JPanel form = new JPanel(new GridLayout(4, 1, 5, 5));
        
        comboTipo = new JComboBox<>(new String[]{"Atestado Médico", "Pedido de Exame", "Receita"});
        
        txtDiagnostico = new JTextArea(3, 20); // Linhas, Colunas
        txtRecomendacao = new JTextArea(5, 20);
        
        
        JScrollPane scrollDiag = new JScrollPane(txtDiagnostico);
        JScrollPane scrollRec = new JScrollPane(txtRecomendacao);

        form.add(new JLabel("Tipo de Documento:"));
        form.add(comboTipo);
        form.add(new JLabel("CPF do paciente: "));
        form.add(patientCPF);
        form.add(new JLabel("Diagnóstico / Motivo:"));
        form.add(scrollDiag);
        form.add(new JLabel("Recomendação / Prescrição:"));
        form.add(scrollRec);

        medicPanel.add(form, BorderLayout.CENTER);

        
        JButton btnEmitir = new JButton("Emitir e Salvar");
        btnEmitir.addActionListener(e -> {
            String tipo = (String) comboTipo.getSelectedItem();
            String diag = txtDiagnostico.getText();
            String rec = txtRecomendacao.getText();

            if (diag.isEmpty() || rec.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

        
            MedicalDocument doc = new MedicalDocument(tipo, user.getName(), patientCPF.getText(), diag, rec);
        
        
            DocumentController controller = new DocumentController();
            controller.emitirDocumento(doc);

            JOptionPane.showMessageDialog(this, "Documento emitido com sucesso!");
        
            txtDiagnostico.setText("");
            txtRecomendacao.setText("");
        });
        
        medicPanel.add(btnEmitir, BorderLayout.SOUTH);
    
        return medicPanel;
    }
    
    //gera o campo formatado utilizando uma máscara. Aqui é usado apenas para o CPF do paciente
    private JFormattedTextField criarCampoFormatado(String mascara) {
        JFormattedTextField campo = null;
        try {
            MaskFormatter format = new MaskFormatter(mascara);
            format.setPlaceholderCharacter('_'); 
            campo = new JFormattedTextField(format);
            campo.setColumns(20);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return campo;
    }
}   
package br.ufjf.dcc025.trabalho2.view.UserInterface;

import br.ufjf.dcc025.trabalho2.controller.AppointmentController;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.User;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MedicPanel <Medic> extends UserPanel {
    
    private AppointmentController consultController;
    private DefaultTableModel appoint;
    private List<Appointment> agenda;
    private DefaultListModel<String> listModel;
    private JList<String> listaVisual;
    private JComboBox comboDias;
    private JSpinner spinnerInicio;
    private JSpinner spinnerFim;
    
    public MedicPanel(MainFrame main) {
        super(main);
        
        this.tabbedPane.addTab("Agendamentos", createAppointmentPage());
         this.tabbedPane.addTab("Gerenciar Agenda", DoctorSchedulePanel());
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
    
    public JPanel DoctorSchedulePanel() {
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        
        JPanel panelForm = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelForm.setBorder(BorderFactory.createTitledBorder("Configurar Disponibilidade"));

        
        String[] diasSemana = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};
        JComboBox comboDias = new JComboBox<>(diasSemana);

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
        
        // Carrega o que o médico JÁ TINHA salvo anteriormente
        /*if (medico.getDisponibilidade() != null) {
            for (String h : medico.getDisponibilidade()) {
                listModel.addElement(h);
            }
        }*/

        listaVisual = new JList<>(listModel);
        painel.add(new JScrollPane(listaVisual), BorderLayout.CENTER);

        
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton btnRemover = new JButton("Remover Selecionado");
        JButton btnSalvarTudo = new JButton("Salvar Agenda");

        btnRemover.addActionListener(e -> removeSelected());
        
        btnSalvarTudo.addActionListener(e -> {
            //savesOnMedic();
            //JOptionPane.showMessageDialog(this, "Agenda do Dr. " + medico.getNome() + " atualizada!");
        });

        panelBotoes.add(btnRemover);
        panelBotoes.add(btnSalvarTudo);

        painel.add(panelBotoes, BorderLayout.SOUTH);
        
        return painel;
    }

    

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

    private void addItemInList() {
        
        String dia = (String) comboDias.getSelectedItem();
        Date inicio = (Date) spinnerInicio.getValue();
        Date fim = (Date) spinnerFim.getValue();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String horarioFormatado = dia + ": " + sdf.format(inicio) + " - " + sdf.format(fim);

        if (listModel.contains(horarioFormatado)) {
            JOptionPane.showMessageDialog(this, "Este horário já foi adicionado!");
            return;
        }

        listModel.addElement(horarioFormatado);
    }

    private void removeSelected() {
        int index = listaVisual.getSelectedIndex();
        if (index != -1) {
            listModel.remove(index);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um horário na lista para remover.");
        }
    }
    /*
    private void savesOnMedic() {
        // 1. Cria uma nova lista limpa
        List<String> novaAgenda = new ArrayList<>();
        
        // 2. Passa tudo que está na tela (ListModel) para a Lista Java
        for (int i = 0; i < listModel.size(); i++) {
            novaAgenda.add(listModel.getElementAt(i));
        }

        // 3. Atualiza o objeto Médico
        medicoAtual.setDisponibilidade(novaAgenda);

        // 4. AQUI VOCÊ CHAMARIA O CONTROLLER PARA SALVAR NO JSON
        // ex: doctorController.atualizarMedico(medicoAtual);
        System.out.println("Salvo no objeto: " + medicoAtual.getDisponibilidade());
    }
    */
}

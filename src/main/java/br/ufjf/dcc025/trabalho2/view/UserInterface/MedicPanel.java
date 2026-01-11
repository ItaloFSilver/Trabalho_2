package br.ufjf.dcc025.trabalho2.view.UserInterface;

import br.ufjf.dcc025.trabalho2.controller.AppointmentController;
import br.ufjf.dcc025.trabalho2.controller.MedicController;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidDateException;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import br.ufjf.dcc025.trabalho2.model.users.Medic;

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

import br.ufjf.dcc025.trabalho2.model.services.WorkShift;
import java.text.ParseException;


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
            //puxa o User armazenado na main pra acessar os dados
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

    public String getHour(int Hours, int Minutes){        //retorna a data formatada corretamente
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
        Date inicio = (Date) spinnerInicio.getValue();
        Date fim = (Date) spinnerFim.getValue();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String horarioFormatado = dia + ": " + sdf.format(inicio) + " - " + sdf.format(fim);

        if (listModel.contains(dia)) {
            JOptionPane.showMessageDialog(this, "Este horário já foi adicionado!");
            return;
        }

        listModel.addElement(horarioFormatado);
    }

}

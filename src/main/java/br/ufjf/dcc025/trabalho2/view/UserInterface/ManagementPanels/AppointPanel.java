package br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels;

import br.ufjf.dcc025.trabalho2.controller.AppointmentController;
import br.ufjf.dcc025.trabalho2.controller.SecretaryController;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.Patient;
import br.ufjf.dcc025.trabalho2.model.users.User;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class AppointPanel extends JPanel {

    private JComboBox<String> comboMedico;      //**************************SOCORRO*************************
    private JComboBox<String> comboPaciente;    //**************************SOCORRO*************************
    private JSpinner spinnerDataHora;           //**************************SOCORRO*************************
    private JCheckBox checkConfirmada;          //**************************SOCORRO*************************
    private SecretaryController controller;     //**************************SOCORRO*************************
    private DefaultTableModel model;            //**************************SOCORRO*************************
    private JFrame frame;

    public AppointPanel(List<Appointment> agenda, DefaultTableModel tableAppoint, JFrame frame) {
      
        this.frame = frame;
        this.model = tableAppoint;
        setLayout(new BorderLayout());
        controller = new SecretaryController();
        List<User> patients = controller.listPatients();
        List<User> medics = controller.listMedics();
        
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Dados da Consulta")); 

        comboMedico = new JComboBox<>();
        for(User u : medics)
            comboMedico.addItem(u.getName() + "-"+ u.getCPF());
        
        comboPaciente = new JComboBox<>();
        for(User u : patients)
            comboPaciente.addItem(u.getName()+"-"+u.getCPF());
        
        SpinnerDateModel modelData = new SpinnerDateModel();
        spinnerDataHora = new JSpinner(modelData);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerDataHora, "dd/MM/yyyy | HH:mm");
        spinnerDataHora.setEditor(editor);
        
        checkConfirmada = new JCheckBox("Confirmada?");
        
        panelForm.add(new JLabel("Médico:")); panelForm.add(comboMedico);
        panelForm.add(new JLabel("Paciente:")); panelForm.add(comboPaciente);
        panelForm.add(new JLabel("Data/Hora:")); panelForm.add(spinnerDataHora);
        panelForm.add(new JLabel("Status:")); panelForm.add(checkConfirmada);

        add(panelForm, BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalvar = new JButton("Agendar");
        JButton btnLimpar = new JButton("Limpar"); 
        
        btnSalvar.addActionListener(e -> saveAppointment(medics, patients, agenda));
        btnLimpar.addActionListener(e -> clearFields());

        //panelBotoes.add(btnLimpar);
        panelBotoes.add(btnSalvar);

        add(panelBotoes, BorderLayout.SOUTH);
    }

    private void saveAppointment(List<User> m, List<User> p, List<Appointment> agenda) {
        
        AppointmentController control = new AppointmentController();
        
        String checar;
        if(checkConfirmada.isSelected())
            checar = "Confirmada";
        else
            checar = "Aguardando Confirmação";
        
        int indMed = comboMedico.getSelectedIndex();
        int indPac = comboPaciente.getSelectedIndex();
        
        Date data = (Date) spinnerDataHora.getValue();
        agenda.add(new Appointment(m.get(indMed), p.get(indPac), data, checkConfirmada.isSelected()));
        control.saveAppointment(new Appointment(m.get(indMed), p.get(indPac), data, checkConfirmada.isSelected()));
        String [] dados = {data.toString(), p.get(indPac).getName(), m.get(indMed).getName(), checar};
        model.addRow(dados);
        
        JOptionPane.showMessageDialog(this, "Consulta agendada para: " + p.get(indPac).getName() + " com Dr. " + m.get(indMed).getName());
        
        clearFields();
        closeWindow();
    }
    
    
    private void clearFields() {
        comboMedico.setSelectedIndex(0);
        comboPaciente.setSelectedIndex(0);
        spinnerDataHora.setValue(new Date()); // Reseta para data de hoje
        checkConfirmada.setSelected(false);
    }
    private void closeWindow(){
        frame.setVisible(false);
    }
}
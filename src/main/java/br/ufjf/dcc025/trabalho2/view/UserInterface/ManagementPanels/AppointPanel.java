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

    private JComboBox<String> comboMedico;
    private JComboBox<String> comboPaciente;
    private JSpinner spinnerDataHora;
    private JCheckBox checkConfirmada;
    private SecretaryController controller;
    private DefaultTableModel model;
    private JFrame frame;

    public AppointPanel(DefaultTableModel tableAppoint, JFrame frame) {
        // Layout principal do Painel
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
        int indMed = comboMedico.getSelectedIndex();
        int indPac = comboPaciente.getSelectedIndex();
        btnSalvar.addActionListener(e -> saveAppointment(medics.get(indMed), patients.get(indPac)));
        btnLimpar.addActionListener(e -> clearFields());

        //panelBotoes.add(btnLimpar);
        panelBotoes.add(btnSalvar);

        add(panelBotoes, BorderLayout.SOUTH);
    }

    private void saveAppointment(User m, User p) {
        
        AppointmentController control = new AppointmentController();
        
        String checar;
        if(checkConfirmada.isSelected())
            checar = "Confirmada";
        else
            checar = "Aguardando Confirmação";
        
        Date data = (Date) spinnerDataHora.getValue();
        
        control.saveAppointment(new Appointment(m, p, data, checkConfirmada.isSelected()));
        String [] dados = {data.toString(), p.getName(), m.getName(), checar};
        model.addRow(dados);
        
        JOptionPane.showMessageDialog(this, "Consulta agendada para: " + p.getName() + " com Dr. " + m.getName());
        
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
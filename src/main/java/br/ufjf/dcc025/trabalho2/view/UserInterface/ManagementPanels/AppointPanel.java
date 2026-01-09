package br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels;

import br.ufjf.dcc025.trabalho2.controller.AppointmentController;
import br.ufjf.dcc025.trabalho2.controller.SecretaryController;
import br.ufjf.dcc025.trabalho2.model.users.User;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class AppointPanel extends JPanel {

    private JComboBox<String> comboMedico;
    private JComboBox<String> comboPaciente;
    private JSpinner spinnerDataHora;
    private JCheckBox checkConfirmada;
    private SecretaryController controller;

    public AppointPanel() {
        // Layout principal do Painel
        setLayout(new BorderLayout());
        controller = new SecretaryController();
        List<User> patients = controller.listPatients();
        List<User> medics = controller.listMedics();
        
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Dados da Consulta")); 

        comboMedico = new JComboBox<>();
        for(User u : medics)
            comboMedico.addItem(u.getName()+ " " + u.getCPF());
        
        comboPaciente = new JComboBox<>();
        for(User u : patients)
            comboPaciente.addItem(u.getName()+ " " + u.getCPF());
            
        SpinnerDateModel modelData = new SpinnerDateModel();
        spinnerDataHora = new JSpinner(modelData);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerDataHora, "dd/MM/yyyy HH:mm");
        spinnerDataHora.setEditor(editor);
        
        checkConfirmada = new JCheckBox("Confirmada?");

        panelForm.add(new JLabel("Médico:")); panelForm.add(comboMedico);
        panelForm.add(new JLabel("Paciente:")); panelForm.add(comboPaciente);
        panelForm.add(new JLabel("Data/Hora:")); panelForm.add(spinnerDataHora);
        panelForm.add(new JLabel("Status:")); panelForm.add(checkConfirmada);

        add(panelForm, BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalvar = new JButton("Agendar");
        JButton btnLimpar = new JButton("Limpar"); // Mudamos de "Cancelar" para "Limpar"

        btnSalvar.addActionListener(e -> saveAppointment());
        btnLimpar.addActionListener(e -> clearFields());

        //panelBotoes.add(btnLimpar);
        panelBotoes.add(btnSalvar);

        add(panelBotoes, BorderLayout.SOUTH);
    }

    private void saveAppointment() {
        
        
        String medico = (String) comboMedico.getSelectedItem();
        String paciente = (String) comboPaciente.getSelectedItem();
        Date data = (Date) spinnerDataHora.getValue();
        
        JOptionPane.showMessageDialog(this, "Consulta agendada para: " + paciente + " com Dr. " + medico);
        
        clearFields();
    }

    private void clearFields() {
        comboMedico.setSelectedIndex(0);
        comboPaciente.setSelectedIndex(0);
        spinnerDataHora.setValue(new Date()); // Reseta para data de hoje
        checkConfirmada.setSelected(false);
    }
}
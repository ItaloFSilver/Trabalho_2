/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

import br.ufjf.dcc025.trabalho2.controller.AppointmentController;
import br.ufjf.dcc025.trabalho2.controller.MedicController;
import br.ufjf.dcc025.trabalho2.controller.SecretaryController;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidAppointmentException;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.User;

public class AppointmentPanel extends JPanel {

    private final JComboBox<String> comboMedico;      
    private final JComboBox<String> comboPaciente;    
    private final JSpinner spinnerDataHora;          
    private final JCheckBox checkConfirmada;         
    private final SecretaryController controller;    
    private final DefaultTableModel model;           
    private final JFrame frame;
    private final JComboBox<String> comboHorario;

    public AppointmentPanel(List<Appointment> agenda, DefaultTableModel tableAppoint, JFrame frame) {
      
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
        
        Calendar cal = Calendar.getInstance();
        
        comboHorario = new JComboBox<>();
        
        
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
        JButton btnCancelar = new JButton("Cancelar"); 
        
        btnSalvar.addActionListener(e -> saveAppointment(medics, patients, agenda));
        btnCancelar.addActionListener(e -> cancelSaving());

        panelBotoes.add(btnCancelar);
        panelBotoes.add(btnSalvar);

        add(panelBotoes, BorderLayout.SOUTH);
    }

    private void saveAppointment(List<User> m, List<User> p, List<Appointment> agenda) {
        
        AppointmentController control = new AppointmentController();
        MedicController medicCon = new MedicController();
        
        String checar;
        if(checkConfirmada.isSelected())
            checar = "Confirmada";
        else
            checar = "Aguardando Confirmação";
        
        int indMed = comboMedico.getSelectedIndex();
        int indPac = comboPaciente.getSelectedIndex();
        Medic medic = (Medic) m.get(indMed);
        Date data = (Date) spinnerDataHora.getValue();
        if(medicCon.medicoAtendeNestaData(medic, data)){
            agenda.add(new Appointment(m.get(indMed), p.get(indPac), data, checkConfirmada.isSelected()));
            try{
                control.saveAppointment(new Appointment(m.get(indMed), p.get(indPac), data, checkConfirmada.isSelected()));
                medicCon.lockTime(medic, data);
            } catch (InvalidAppointmentException e){
                JOptionPane.showMessageDialog(this, e.getMessage());
                return;
            }
            String [] dados = {data.toString(), p.get(indPac).getName(), m.get(indMed).getName(), checar};
            
            model.addRow(dados);
        
            JOptionPane.showMessageDialog(this, "Consulta agendada para: " + p.get(indPac).getName() + " com Dr. " + m.get(indMed).getName());
        
            clearFields();
            closeWindow();
        }
        else{
            JOptionPane.showMessageDialog(this, "Confira os dias de atendimento do médico!");
        }
    }
    
    //ação do botão de cancelar: limpa os campos e fecha a janela
    private void cancelSaving(){
        clearFields();
        closeWindow();
    }
    
    //limpa os campos que tenham sido preenchidos
    private void clearFields() {
        if(comboMedico != null)
            comboMedico.setSelectedIndex(0);
        comboPaciente.setSelectedIndex(0);
        spinnerDataHora.setValue(new Date()); // Reseta para data de hoje
        checkConfirmada.setSelected(false);
    }
    
    //fecha a página de registro de agendamento
    private void closeWindow(){
        frame.setVisible(false);
    }
}
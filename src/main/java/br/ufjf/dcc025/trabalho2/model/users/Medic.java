package br.ufjf.dcc025.trabalho2.model.users;

import br.ufjf.dcc025.trabalho2.controller.MedicController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidDateException;
import br.ufjf.dcc025.trabalho2.model.services.WorkShift;
import br.ufjf.dcc025.trabalho2.view.UserInterface.MainFrame;
import br.ufjf.dcc025.trabalho2.view.UserInterface.MedicPanel;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


public class Medic extends User {    
    private Specialization specialization;
    private boolean active;
    //private List<String> agenda;
    


    public Medic(String name, String email, String cpf, String phoneNumber, String password) {
        super(name, email, cpf, phoneNumber, password);
        this.profile = Profile.MEDICO;
    }

    public Medic(String name, Email email, CPF cpf, PhoneNumber phoneNumber, Password password) {
        super(name, email, cpf, phoneNumber, password, true);
        this.profile = Profile.MEDICO;
        
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public List<WorkShift> getDisponibilityAsList() {
        MedicController medic = new MedicController();
        
        return medic.loadWorkShift(this.getCPF());
    }

    @Override
    public JPanel createPanel(MainFrame mainframe) {
        return new MedicPanel(mainframe, this);
    }
    
    @Override 
    public boolean getStatus(){
        return this.active;
    }
    @Override 
    public void setStatus(boolean sts){
        this.status = sts;
    }
    
    public List<String> getFreeTime(int dds){
        List<String> horarios = new ArrayList<>();
        horarios = getDisponibilityAsList().get(dds).getFreeTime();
        
        return horarios;
    }
    
    public void lockTime(Date dataAgendamento){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataAgendamento);
        int diaNumero = cal.get(Calendar.DAY_OF_WEEK);
        String diaTexto = "";
        switch (diaNumero) {
        case Calendar.SUNDAY:    diaTexto = "Domingo-feira"; break;
        case Calendar.MONDAY:    diaTexto = "Segunda-feira"; break;
        case Calendar.TUESDAY:   diaTexto = "Terca-feira"; break;
        case Calendar.WEDNESDAY: diaTexto = "Quarta-feira"; break;
        case Calendar.THURSDAY:  diaTexto = "Quinta-feira"; break;
        case Calendar.FRIDAY:    diaTexto = "Sexta-feira"; break;
        case Calendar.SATURDAY:  diaTexto = "Sabado"; break;
        
        }
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        String horaDoDia = parser.format(dataAgendamento);
        for(WorkShift u : this.getDisponibilityAsList())
            if(u.getDayOfWeek().toString().equals(diaTexto) && u.getFreeTime().contains(horaDoDia))
                u.timeBlock(dataAgendamento);
    }
    
    public boolean medicoAtendeNestaData(Date dataConsulta) {
    
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataConsulta);
        int diaNumero = cal.get(Calendar.DAY_OF_WEEK);

        String diaTexto = "";
        switch (diaNumero) {
            case Calendar.SUNDAY:    diaTexto = "Domingo-feira"; break;
            case Calendar.MONDAY:    diaTexto = "Segunda-feira"; break;
            case Calendar.TUESDAY:   diaTexto = "Terca-feira"; break;
            case Calendar.WEDNESDAY: diaTexto = "Quarta-feira"; break;
            case Calendar.THURSDAY:  diaTexto = "Quinta-feira"; break;
            case Calendar.FRIDAY:    diaTexto = "Sexta-feira"; break;
            case Calendar.SATURDAY:  diaTexto = "Sabado"; break;
        }

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        String horaDoDia = parser.format(dataConsulta);
    
        for (WorkShift horario : this.getDisponibilityAsList()) {
            if (horario.getDayOfWeek().toString().equals(diaTexto) && horario.getFreeTime().contains(horaDoDia)) {
                return true; 
            }
        }
    
        return false; 
    }
 
}
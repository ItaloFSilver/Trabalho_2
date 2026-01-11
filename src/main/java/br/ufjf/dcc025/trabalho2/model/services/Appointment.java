package br.ufjf.dcc025.trabalho2.model.services;

import java.util.Date;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.Patient;
import br.ufjf.dcc025.trabalho2.model.users.User;
import java.util.Calendar;

public class Appointment {
   
    private final CPF medicCPF;
    private final CPF patientCPF;
    private final Date date;
    private final String patientName;
    private final String medicName;
    private boolean confirmed;
    private final Medic medic;
    private final Patient patient;
    
    //private final User medic; // <- não sei se vai precisar disso aqui

    public Appointment(User medic, User patient, Date date, boolean check) {
        this.medicCPF = medic.getCPF();
        this.patientCPF = patient.getCPF();
        this.date = date;
        this.medicName = medic.getName();
        this.patientName = patient.getName();
        this.confirmed = check;
        this.medic = (Medic)medic; 
        this.patient = (Patient)patient;
    }

    public String getMedicName(){
        return this.medicName;
    }
    public Medic getMedic(){
        return medic;
    }
    
    public Patient getPatient(){
        return patient;
    }
    
    public CPF getMedicCPF() {
        return this.medicCPF;
    }
    
    public String getPatientName(){
        return this.patientName;
    }
    public CPF getPatientCPF() {
        return this.patientCPF;
    }
    public Date getData(){
        return this.date;
    }
    
    public String getDate(){        //retorna a data formatada corretamente
        String day = "";
        String month = "";
        String year = ""+(date.getYear()-100);
        String hour = "";
        String minute = "";
                
        if(this.date.getDate() < 10)
            day += "0";
        day += "" + (this.date.getDate());
        
        if(this.date.getMonth()+1 < 10)
            month += "0";
        month += "" + (this.date.getMonth()+1);
        if(this.date.getHours() < 10)
            hour += "0";
        hour += "" + (this.date.getHours());
        
        if(this.date.getMinutes() < 10)
            minute += "0";
        minute += "" + (this.date.getMinutes());
        
        String fullDate = day + "/"+ month + "/" + year + " " + hour + ":" + minute;
        
        return fullDate;
    }
    
    public int getDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK)-1;
    }
    
    public String getCheck(){
        if(confirmed)
            return "Confirmada";
        return "Aguardando Confirmação";
    }
    
    @Override
    public int hashCode() {
        return Appointment.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Appointment other = (Appointment) obj;
        return medicCPF.equals(other.medicCPF) && patientCPF.equals(other.patientCPF) && date.equals(other.date);
    }
}
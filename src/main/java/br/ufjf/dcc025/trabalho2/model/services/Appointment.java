package br.ufjf.dcc025.trabalho2.model.services;

import java.util.Date;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.users.User;

public class Appointment {
   
    private final CPF medicCPF;
    private final CPF patientCPF;
    private final Date date;
    private final String patientName;
    private final String medicName;
    private boolean confirmed;
    //private final User medic; // <- não sei se vai precisar disso aqui

    public Appointment(User medic, User patient, Date date, boolean check) {
        this.medicCPF = medic.getCPF();
        this.patientCPF = patient.getCPF();
        this.date = date;
        this.medicName = medic.getName();
        this.patientName = patient.getName();
        this.confirmed = check;
        //this.medic = medic; // <- idem
    }

    public String getMedicName(){
        return this.medicName;
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
    
    public String getDate(){
        String day = "";
        String month = "";
        
        if(this.date.getDate() < 10)
            day += "0";
        day += "" + (this.date.getDate());
        
        if(this.date.getMonth()+1 < 10)
            month += "0";
        month += "" + (this.date.getMonth()+1);
        
        String fullDate = day + "/"+ month;
        
        return fullDate;
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
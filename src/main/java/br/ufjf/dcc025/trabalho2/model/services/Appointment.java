/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
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
    private boolean confirmed;
    private final Medic medic;
    private final Patient patient;

    public Appointment(User medic, User patient, Date date, boolean check) {
        this.medicCPF = medic.getCPF();
        this.patientCPF = patient.getCPF();
        this.date = date;
        this.confirmed = check;
        this.medic = (Medic)medic; 
        this.patient = (Patient)patient;
    }

    //retorna o nome do médico registrado no agendamento
    public String getMedicName(){
        return this.medic.getName();
    }
    //retorna o médico
    public Medic getMedic(){
        return medic;
    }
    
    //retorna o paciente
    public Patient getPatient(){
        return patient;
    }
    
    //retorna o cpf do médico
    public CPF getMedicCPF() {
        return this.medicCPF;
    }
    
    //retorna o nome do paciente
    public String getPatientName(){
        return this.patient.getName();
    }
    
    //retorna o CPF do paciente
    public CPF getPatientCPF() {
        return this.patientCPF;
    }
    
    //retorna a data do agendamento como DATE
    public Date getData(){
        return this.date;
    }
    
    //retorna a data formatada em String
    public String getDate(){        
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
    
    //retorna o dia como inteiro 1-7
    public int getDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.date);
        cal.get(Calendar.DAY_OF_WEEK);
        
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    
    //confirma o status da consulta(para controle de frequência)
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
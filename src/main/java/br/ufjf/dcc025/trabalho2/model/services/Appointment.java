package br.ufjf.dcc025.trabalho2.model.services;

import java.time.LocalDate;
import java.time.LocalTime;


import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.Patient;

public class Appointment {
   
    private String medicID;
    private String patientID;
    private LocalDate date;
    private LocalTime time;

    public Appointment(Medic medic, Patient patient, LocalDate date, LocalTime time) {
        this.medicID = medic.getCPF().toString();
        this.patientID = patient.getCPF().toString();
        this.date = date;
        this.time = time;
    }

    public String getMedicId() {
        String ID = this.medicID;
        return ID;
    }

    public String getPatientId() {
        String ID = this.patientID;
        return ID;
    }
    
    public String getDate(){
        return date.toString();
    }
    
    public String getTime(){
        return time.toString();
    }
    
}
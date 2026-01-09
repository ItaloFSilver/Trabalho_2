package br.ufjf.dcc025.trabalho2.model.services;

import java.time.LocalDate;
import java.time.LocalTime;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.Patient;

public class Appointment {
   
    private CPF medicCPF;
    private CPF patientCPF;
    private LocalDate date;
    private LocalTime time;

    public Appointment(Medic medic, Patient patient, LocalDate date, LocalTime time) {
        this.medicCPF = medic.getCPF();
        this.patientCPF = patient.getCPF();
        this.date = date;
        this.time = time;
    }

    public CPF getMedicCPF() {
        return this.medicCPF;
    }

    public CPF getPatientCPF() {
        return this.patientCPF;
    }
    
    public String getDate(){
        return date.toString();
    }
    
    public String getTime(){
        return time.toString();
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
        return medicCPF.equals(other.medicCPF) && patientCPF.equals(other.patientCPF) && date.equals(other.date) && time.equals(other.time);
    }
}
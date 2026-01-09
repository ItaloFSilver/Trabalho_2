package br.ufjf.dcc025.trabalho2.model.services;

import java.sql.Date;
import java.time.LocalDate;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.Patient;

public class Appointment {
   
    private CPF medicCPF;
    private CPF patientCPF;
    private Date date;

    public Appointment(Medic medic, Patient patient, LocalDate date) {
        this.medicCPF = medic.getCPF();
        this.patientCPF = patient.getCPF();
        this.date = Date.valueOf(date);
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
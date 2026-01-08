package br.ufjf.dcc025.trabalho2.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.Patient;

public class Appointment {
   
    private UUID medicID;
    private UUID patientID;
    private LocalDate date;
    private LocalTime time;

    public Appointment(Medic medic, Patient patient, LocalDate date, LocalTime time) {
        this.medicID = medic.getId();
        this.patientID = patient.getId();
        this.date = date;
        this.time = time;
    }
}
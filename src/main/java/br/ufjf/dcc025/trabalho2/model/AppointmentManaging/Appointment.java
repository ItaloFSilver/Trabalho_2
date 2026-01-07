package br.ufjf.dcc025.trabalho2.model.AppointmentManaging;

public class Appointment {
    public String id;
    public String medicID;
    public String pacientID;

    public Appointment(String id, String medicID, String pacientID) {
        this.id = id;
        this.medicID = medicID;
        this.pacientID = pacientID;
    }

    public String getId() {
        return this.id;
    }
}

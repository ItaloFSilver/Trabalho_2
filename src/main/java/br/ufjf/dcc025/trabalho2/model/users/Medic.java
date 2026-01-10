package br.ufjf.dcc025.trabalho2.model.users;

import java.util.List;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;

public class Medic extends User {    
    private Specialization specialization;
    private List<Appointment> appointments;
    private boolean active;

    public Medic(String name, String email, String cpf, String phoneNumber, String password) {
        super(name, email, cpf, phoneNumber, password);
        this.profile = Profile.MEDICO;
        this.active = true;
    }

    public Medic(String name, Email email, CPF cpf, PhoneNumber phoneNumber, Password password) {
        super(name, email, cpf, phoneNumber, password);
        this.profile = Profile.MEDICO;
    }

    public void setAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        for(Appointment a : this.appointments) {
            if(a.equals(appointment)) {
                this.appointments.remove(a);
                break;
            }
        }
    }

    public Specialization getSpecialization() {
        return specialization;
    }
    
}
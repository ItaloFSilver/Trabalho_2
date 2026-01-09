package br.ufjf.dcc025.trabalho2.controller;

import br.ufjf.dcc025.trabalho2.model.repository.AppointmentRepository;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;

public class AppointmentController {
    
    public void saveAppointment(Appointment appointment) {
        new AppointmentRepository().save(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        new AppointmentRepository().remove(appointment);
    }
}

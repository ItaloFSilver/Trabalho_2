package br.ufjf.dcc025.trabalho2.controller;

import br.ufjf.dcc025.trabalho2.model.repository.AppointmentRepository;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController {
    
    public void saveAppointment(Appointment appointment) {
        new AppointmentRepository().save(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        Appointment a = new AppointmentRepository().searchByCPF(appointment.getMedicCPF(), appointment.getPatientCPF());
        new AppointmentRepository().remove(appointment);
    }

    public List<Appointment> listAll(){
        return new AppointmentRepository().listAll();
    }
    
    public List<Appointment> listThis(String cpf){
        List<Appointment> appointments = new AppointmentRepository().listAll(), hisAppointments = new ArrayList<>();
        for(Appointment a : appointments)
            if(a.getPatientCPF().toString().equals(cpf))
                hisAppointments.add(a);
        return hisAppointments;
    }
}

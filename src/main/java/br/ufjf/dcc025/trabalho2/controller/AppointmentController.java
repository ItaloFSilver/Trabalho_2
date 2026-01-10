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
    
    public void removeAllOfUser(String cpf){
        List<Appointment> list = listThis(cpf);
        while(!list.isEmpty()){
            new AppointmentRepository().remove(list.getLast());
            list.removeLast();
        }
    }
    
    public List<Appointment> listThis(String cpf){  //Pesquisa os agendamentos do CPF, assim a gente puxa só as de cada usuário pra eles mesmos
        List<Appointment> appointments = new AppointmentRepository().listAll(), hisAppointments = new ArrayList<>();
        for(Appointment a : appointments)
            if(a.getPatientCPF().toString().equals(cpf) || a.getMedicCPF().toString().equals(cpf))
                hisAppointments.add(a);
        return hisAppointments;
    }
}

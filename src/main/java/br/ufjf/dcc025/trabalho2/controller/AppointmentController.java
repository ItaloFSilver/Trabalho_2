package br.ufjf.dcc025.trabalho2.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidAppointmentException;
import br.ufjf.dcc025.trabalho2.model.repository.AppointmentRepository;
import br.ufjf.dcc025.trabalho2.model.repository.UserRepository;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import br.ufjf.dcc025.trabalho2.model.users.User;

public class AppointmentController {
    
    public void saveAppointment(Appointment appointment) throws InvalidAppointmentException {
        List<Appointment> appointments = new AppointmentRepository().listAll();
        for(Appointment a : appointments) {
            if(a.getMedicCPF().equals(appointment.getMedicCPF()) && a.getDate().equals(appointment.getDate())) {
                throw new InvalidAppointmentException("O Médico já possui uma consulta nessa data");
            }
            if(a.getPatientCPF().equals(appointment.getPatientCPF()) && a.getDate().equals(appointment.getDate())){
                throw new InvalidAppointmentException("Paciente já possui uma consulta nessa data");
            }
        }

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
        UserRepository users = new UserRepository();
        
        for(Appointment a : appointments){
            if(a.getPatientCPF().toString().equals(cpf) && users.findByCPF(a.getMedicCPF().toString()) != null || a.getMedicCPF().toString().equals(cpf) && users.findByCPF(a.getPatientCPF().toString()) != null)
                hisAppointments.add(a);
        }
        return hisAppointments;
    }
}

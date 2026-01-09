package br.ufjf.dcc025.trabalho2.model.repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.Patient;

public class AppointmentRepository implements Repository<Appointment> {
    private final String path = dirPath + File.separator + "servicesData" + File.separator + "appointmentsData.json"; 
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void save(Appointment appointment) {
        List<Appointment> appointments = listAll();
        appointments.add(appointment);
        File file = new File(path);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(appointments, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Appointment appointment) {
    }

    @Override
    public List<Appointment> listAll() {
        File file = new File(path);
        List<Appointment> appointments; 
        try (FileReader fileReader = new FileReader(file)){
            Type typeList = new TypeToken<ArrayList<Appointment>>(){}.getType();    
            appointments = gson.fromJson(fileReader, typeList);
            return appointments == null ? new ArrayList<>() : appointments;

        } 
        catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }    
    
    public Appointment searchByCPF(Medic medic) {
        List<Appointment> appointments = listAll();
        for (Appointment appointment : appointments) {
            if (appointment.getMedicCPF().equals(medic.getCPF())) {
                return appointment;
            }
        }
        return null;
    }

    public Appointment searchByCPF(Patient patient) {
        List<Appointment> appointments = listAll();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientCPF().equals(patient.getCPF())) {
                return appointment;
            }
        }
        return null;
    }
}
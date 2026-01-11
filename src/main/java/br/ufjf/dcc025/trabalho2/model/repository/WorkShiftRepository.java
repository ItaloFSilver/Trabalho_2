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

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidDateException;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidRemoveException;
import static br.ufjf.dcc025.trabalho2.model.repository.Repository.dirPath;
import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import br.ufjf.dcc025.trabalho2.model.services.WorkShift;

public class WorkShiftRepository implements Repository<WorkShift> {
    private final String path = dirPath + File.separator + "servicesData" + File.separator + "workShiftsData.json"; 
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void save(WorkShift a) throws InvalidDateException{
        List<WorkShift> workShifts = listAll();
        for(WorkShift b : workShifts){
            if(a.getDayOfWeek().equals(b.getDayOfWeek()))
                throw new InvalidDateException("Dia já registrado");
        }workShifts.add(a);
        File file = new File(path);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(workShifts, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(WorkShift workShift) {   //To me questionando se isso tá ok, mas funciona
        List<WorkShift> workShifts = listAll();

        for(int i=0; i<workShifts.size(); i++) {
            WorkShift u = workShifts.get(i);
            if(workShift.getMedicCPF().equals(u.getMedicCPF()) && workShift.getStart().equals(u.getStart()) && workShift.getEnd().equals(u.getEnd())) {
                workShifts.remove(u);
                break;
            }
        }
        
        File file = new File(path);
        
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(workShifts, writer);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new InvalidRemoveException("Erro ao remover workShift");
        }
    
    }

    @Override
    public List<WorkShift> listAll() {
        File file = new File(path);
        List<WorkShift> workShifts; 
        try (FileReader fileReader = new FileReader(file)){
            Type typeList = new TypeToken<ArrayList<WorkShift>>(){}.getType();    
            workShifts = gson.fromJson(fileReader, typeList);
            return workShifts == null ? new ArrayList<>() : workShifts;

        } 
        catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }    
    
    public List<WorkShift> searchByCPF(CPF medic) {    
        List<WorkShift> workShifts = listAll(); 
        List<WorkShift> workShifties = new ArrayList<>();
        int i=0;
        for (WorkShift workShift : workShifts) {
            if (workShift.getMedicCPF().equals(medic)) {
                workShifties.add(workShift);
            }
        }
        
        return workShifties;
    }

    
    }

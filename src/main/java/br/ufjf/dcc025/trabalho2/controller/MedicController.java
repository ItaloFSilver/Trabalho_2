package br.ufjf.dcc025.trabalho2.controller;


import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidDateException;
import br.ufjf.dcc025.trabalho2.model.repository.MedicRepository;
import br.ufjf.dcc025.trabalho2.model.repository.WorkShiftRepository;
import br.ufjf.dcc025.trabalho2.model.services.WorkShift;
import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.Profile;
import java.util.Date;
import java.util.List;


public class MedicController {
 
    private WorkShiftRepository repoWS;
    
    public void savesWorkShift(String dayOfWeek, Date start, Date end, Medic medic) throws InvalidDateException {
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        WorkShift workShift;
        repoWS = new WorkShiftRepository();
        try {
            workShift = new WorkShift(dayOfWeek, parser.format(start), parser.format(end), medic.getCPF());
            repoWS.save(workShift);
        }
        catch(InvalidDateException e) {
            throw new InvalidDateException(e.getMessage());
        }

    }
    public void removesWorkShift(String dayOfWeek, Date start, Date end, Medic medic) throws InvalidDateException {
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        WorkShift workShift;
        repoWS = new WorkShiftRepository();
        try {
            workShift = new WorkShift(dayOfWeek, parser.format(start), parser.format(end), medic.getCPF());
            repoWS.remove(workShift);
        }
        catch(InvalidDateException e) {
            throw new InvalidDateException(e.getMessage());
        }
    }
    
    public void removesWorkShift(WorkShift w){
        repoWS = new WorkShiftRepository();
        repoWS.remove(w);
    }
    
    public List<WorkShift> loadWorkShift(CPF cpf) {
        repoWS = new WorkShiftRepository();
        
        return repoWS.searchByCPF(cpf);
    }
}
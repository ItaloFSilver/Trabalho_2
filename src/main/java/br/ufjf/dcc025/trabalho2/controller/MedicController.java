/*Arthur de Souza Marques - 202435015 */
/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidDateException;
import br.ufjf.dcc025.trabalho2.model.repository.WorkShiftRepository;
import br.ufjf.dcc025.trabalho2.model.services.WorkShift;
import br.ufjf.dcc025.trabalho2.model.users.Medic;


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
    
    public void freeTime(Medic medic, Date dataAgendamento){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataAgendamento);
        int diaNumero = cal.get(Calendar.DAY_OF_WEEK);
        String diaTexto = "";
        switch (diaNumero) {
        case Calendar.SUNDAY:    diaTexto = "Domingo-feira"; break;
        case Calendar.MONDAY:    diaTexto = "Segunda-feira"; break;
        case Calendar.TUESDAY:   diaTexto = "Terca-feira"; break;
        case Calendar.WEDNESDAY: diaTexto = "Quarta-feira"; break;
        case Calendar.THURSDAY:  diaTexto = "Quinta-feira"; break;
        case Calendar.FRIDAY:    diaTexto = "Sexta-feira"; break;
        case Calendar.SATURDAY:  diaTexto = "Sabado"; break;
        }
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        String horaDoDia = parser.format(dataAgendamento);
        for(WorkShift u : medic.getDisponibilityAsList())
            if(u.getDayOfWeek().toString().equals(diaTexto) && u.getFreeTime().contains(horaDoDia))
                u.setFree(dataAgendamento);
    }
    
    public void lockTime(Medic medic, Date dataAgendamento){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataAgendamento);
        int diaNumero = cal.get(Calendar.DAY_OF_WEEK);
        String diaTexto = "";
        switch (diaNumero) {
        case Calendar.SUNDAY:    diaTexto = "Domingo-feira"; break;
        case Calendar.MONDAY:    diaTexto = "Segunda-feira"; break;
        case Calendar.TUESDAY:   diaTexto = "Terca-feira"; break;
        case Calendar.WEDNESDAY: diaTexto = "Quarta-feira"; break;
        case Calendar.THURSDAY:  diaTexto = "Quinta-feira"; break;
        case Calendar.FRIDAY:    diaTexto = "Sexta-feira"; break;
        case Calendar.SATURDAY:  diaTexto = "Sabado"; break;
        
        }
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        String horaDoDia = parser.format(dataAgendamento);
        for(WorkShift u : medic.getDisponibilityAsList())
            if(u.getDayOfWeek().toString().equals(diaTexto) && u.getFreeTime().contains(horaDoDia))
                u.timeBlock(dataAgendamento);
    }
    
    public boolean medicoAtendeNestaData(Medic medic, Date dataConsulta) {
    
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataConsulta);
        int diaNumero = cal.get(Calendar.DAY_OF_WEEK);

        String diaTexto = "";
        switch (diaNumero) {
            case Calendar.SUNDAY:    diaTexto = "Domingo-feira"; break;
            case Calendar.MONDAY:    diaTexto = "Segunda-feira"; break;
            case Calendar.TUESDAY:   diaTexto = "Terca-feira"; break;
            case Calendar.WEDNESDAY: diaTexto = "Quarta-feira"; break;
            case Calendar.THURSDAY:  diaTexto = "Quinta-feira"; break;
            case Calendar.FRIDAY:    diaTexto = "Sexta-feira"; break;
            case Calendar.SATURDAY:  diaTexto = "Sabado"; break;
        }

        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        String horaDoDia = parser.format(dataConsulta);
    
        for (WorkShift horario : medic.getDisponibilityAsList()) {
            if (horario.getDayOfWeek().toString().equals(diaTexto) && horario.getFreeTime().contains(horaDoDia)) {
                return true; 
            }
        }
        return false;
    }
}
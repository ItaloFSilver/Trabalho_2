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
    
    public void savesWorkShift(WorkShift w){
        repoWS = new WorkShiftRepository();
        repoWS.save(w);
    }
    
    public void savesWorkShift(String dayOfWeek, Date start, Date end, Medic medic) throws InvalidDateException { //Salva o horário de trabalho do médico de acordo com o dia da semana
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");                                            
        WorkShift workShift;
        repoWS = new WorkShiftRepository();
        workShift = new WorkShift(dayOfWeek, parser.format(start), parser.format(end), medic.getCPF());
        repoWS.save(workShift);
    }
    
    public void removesWorkShift(String dayOfWeek, Date start, Date end, Medic medic) throws InvalidDateException { //remove o dia de trabalho da lista do medico
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        WorkShift workShift;
        repoWS = new WorkShiftRepository();
        workShift = new WorkShift(dayOfWeek, parser.format(start), parser.format(end), medic.getCPF());
        repoWS.remove(workShift); 
    }
    
    public void removesWorkShift(WorkShift w){  //chamada alternativa para remover o dia de trabalho da lista do médico
        repoWS = new WorkShiftRepository();
        repoWS.remove(w);
    }
    
    public List<WorkShift> loadWorkShift(CPF cpf) { //retorna a agenda de trabalho do médico
        repoWS = new WorkShiftRepository();
        
        return repoWS.searchByCPF(cpf);
    }
    
    public void freeTime(Medic medic, Date dataAgendamento){    //retorna os horários disponíveis que o médico possui
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataAgendamento);
        int diaNumero = cal.get(Calendar.DAY_OF_WEEK);
        
        String diaTexto = diaSemana(diaNumero);
        
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        String horaDoDia = parser.format(dataAgendamento);
        for(WorkShift u : medic.getDisponibilityAsList())
            if(u.getDayOfWeek().toString().equals(diaTexto) && u.getFreeTime().contains(horaDoDia)){
                u.setFree(dataAgendamento);
                removesWorkShift(u);
                savesWorkShift(u);
            }
    }
    
    public void lockTime(Medic medic, Date dataAgendamento){    //bloqueia o horário anteriormente disponível
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataAgendamento);
        int diaNumero = cal.get(Calendar.DAY_OF_WEEK);
        
        String diaTexto = diaSemana(diaNumero);
        
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        String horaDoDia = parser.format(dataAgendamento);
        for(WorkShift u : medic.getDisponibilityAsList())
            if(u.getDayOfWeek().toString().equals(diaTexto) && u.getFreeTime().contains(horaDoDia)){
                u.timeBlock(dataAgendamento);
                removesWorkShift(u);
                savesWorkShift(u);
            }
    }
    
    public boolean medicoAtendeNestaData(Medic medic, Date dataConsulta) {  //verifica se o horário está disponível para agendamento
    
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataConsulta);
        int diaNumero = cal.get(Calendar.DAY_OF_WEEK);

        String diaTexto = diaSemana(diaNumero);
        
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        String horaDoDia = parser.format(dataConsulta);
    
        for (WorkShift horario : medic.getDisponibilityAsList()) {
            if (horario.getDayOfWeek().toString().equals(diaTexto) && horario.getFreeTime().contains(horaDoDia)) {
                return true; 
            }
        }
        return false;
    }
    
    public String diaSemana(int dia){       //método para retornar o dia da semana com base no inteiro do Calendar passado
        switch (dia) {
            case Calendar.SUNDAY -> {
                return "Domingo";
            }
            case Calendar.MONDAY -> {
                return "Segunda-feira";
            }
            case Calendar.TUESDAY -> { 
                return "Terca-feira";
            }
            case Calendar.WEDNESDAY -> {
                return "Quarta-feira";
            }
            case Calendar.THURSDAY -> {
                return "Quinta-feira";
            }
            case Calendar.FRIDAY -> {
                return "Sexta-feira";
            }
            case Calendar.SATURDAY -> {
                return "Sabado";
            }
        }
        return "";
    }
}
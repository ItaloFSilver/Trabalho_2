package br.ufjf.dcc025.trabalho2.model.services;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidDateException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WorkShift {
    private DayOfWeek dayOfWeek;
    private String start;
    private String end;
    private CPF medicCPF;
    private List<String> block;

    public WorkShift(String dayOfWeek, String start, String end, CPF cpf)  throws InvalidDateException {
        if(start.charAt(0) >= end.charAt(0) && start.charAt(1) >= end.charAt(1))
            throw new InvalidDateException("Data invalida");
        this.dayOfWeek = DayOfWeek.fromString(dayOfWeek);
        this.start = start;
        this.end = end;
        this.medicCPF = cpf;
        this.block = new ArrayList<>();
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public String getStart() {
        return this.start;
    }
    
    public void timeBlock(Date time){
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        block.add(parser.format(time));
    }
    
    public void setFree(Date time){
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        block.remove(parser.format(time));
    }
    
    public List<String> getFreeTime(){
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        List<String> listaHorarios = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        Calendar calFim = Calendar.getInstance();
        try{
            cal.setTime(parser.parse(start));
            calFim.setTime(parser.parse(end));
        }
        catch(ParseException e){}
    
        while (cal.compareTo(calFim) <= 0) {
           
            Date horarioAtual = cal.getTime();
            listaHorarios.add(sdf.format(horarioAtual));
            cal.add(Calendar.MINUTE, 30);
        }

        return listaHorarios;
    }
    
    public boolean isFree(String time){
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        try{
        if(parser.parse(this.start).before(parser.parse(time)) && parser.parse(this.end).after( parser.parse(time))){
            if(block == null)
                return true;
            return !(block.contains(time));
        }
        return false; 
        }catch(ParseException evt){
            System.out.println("Error on Parse");
        }
        return false;
    }
    
    public String getEnd() {
        return this.end;
    }
    
    public CPF getMedicCPF(){
        return this.medicCPF;
    }

    @Override
    public String toString() {
        
    // Formata o início e o fim para String
        String inicio = this.start;
        String fim = this.end;
    
    // Retorna algo como: "Segunda-feira: 08:00 - 12:00"
        return this.dayOfWeek.toString() + ": " + inicio + " - " + fim;
    }
}

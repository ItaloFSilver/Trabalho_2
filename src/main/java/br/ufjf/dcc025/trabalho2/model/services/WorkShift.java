package br.ufjf.dcc025.trabalho2.model.services;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidDateException;
import java.text.ParseException;
import java.util.ArrayList;
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
    
    public void timeBlock(String time){
        block.add(time);
    }
    
    public void setFree(String time){
        block.remove(time);
    }
    
    public List<String> getFreeTime(){
        List<Date> freeDate = new ArrayList<>();
        List<String> converted = new ArrayList<>();
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        try{
            Date upToDate = parser.parse(start);
            while(upToDate.before(parser.parse(end)) || upToDate.equals( parser.parse(end))){
                if(block == null || !block.contains(start))
                    freeDate.add(parser.parse(start));
                    
                upToDate.setTime(upToDate.getTime());
            }
            int i=0;
            for(Date d : freeDate)
                converted.add(parser.format(freeDate.get(i++)));
        }catch(ParseException e){
            System.exit(1);
        }
       
        return converted;
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

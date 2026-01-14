/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
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

    //retorna o enum do dia da semana
    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }
    
    //retorna o dia da semana como um inteiro
    public int getDayOfWeeki(){
        return switch (this.dayOfWeek) {
            case SUNDAY -> 1;
            case MONDAY -> 2;
            case TUESDAY -> 3;
            case WEDNESDAY -> 4;
            case THURSDAY -> 5;
            case FRIDAY -> 6;
            case SATURDAY -> 7;
            default -> 0;
        };
    }

    //retorna o horário de inicio da consulta
    public String getStart() {
        return this.start;
    }
    
    //bloqueia o horário recebido como parametro
    public void timeBlock(Date time){
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        block.add(parser.format(time));
    }
    
    //libera o horário recebido como parâmetro
    public void setFree(Date time){
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        block.remove(parser.format(time));
    }
    
    //retorna uma lista com todos os horários disponíveis
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
    
    //verifica se o horário está disponível
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
    
    //retorna o horário final do horário de atendimento
    public String getEnd() {
        return this.end;
    }
    
    //retorna o cpf do médico
    public CPF getMedicCPF(){
        return this.medicCPF;
    }

    //retorna o dia com horário de início e fim como String
    @Override
    public String toString() {
        
    // Formata o início e o fim para String
        String inicio = this.start;
        String fim = this.end;
    
    // Retorna algo como: "Segunda-feira: 08:00 - 12:00"
        return this.dayOfWeek.toString() + ": " + inicio + " - " + fim;
    }
}

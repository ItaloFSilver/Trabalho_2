package br.ufjf.dcc025.trabalho2.model.services;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidDateException;

public class WorkShift {
    private DayOfWeek dayOfWeek;
    private String start;
    private String end;
    private CPF medicCPF;

    public WorkShift(String dayOfWeek, String start, String end, CPF cpf)  throws InvalidDateException {
        if(start.charAt(0) >= end.charAt(0) && start.charAt(1) >= end.charAt(1))
            throw new InvalidDateException("Data invalida");
        this.dayOfWeek = DayOfWeek.fromString(dayOfWeek);
        this.start = start;
        this.end = end;
        this.medicCPF = cpf;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public String getStart() {
        return this.start;
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

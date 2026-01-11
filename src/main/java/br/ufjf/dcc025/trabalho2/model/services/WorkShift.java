package br.ufjf.dcc025.trabalho2.model.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidDateException;

public class WorkShift {
    private DayOfWeek dayOfWeek;
    private Date start;
    private Date end;

    public WorkShift(String dayOfWeek, Date start, Date end)  throws InvalidDateException {
        if(start.getHours() >= end.getHours())
            throw new InvalidDateException("Data invalida");
        this.dayOfWeek = DayOfWeek.fromString(dayOfWeek);
        this.start = start;
        this.end = end;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public Date getStart() {
        return this.start;
    }

    public Date getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    
    // Formata o início e o fim para String
        String inicio = sdf.format(this.start);
        String fim = sdf.format(this.end);
    
    // Retorna algo como: "Segunda-feira: 08:00 - 12:00"
        return this.dayOfWeek.toString() + ": " + inicio + " - " + fim;
    }
}

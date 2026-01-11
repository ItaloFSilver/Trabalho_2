package br.ufjf.dcc025.trabalho2.model.services;

import java.sql.Time;

public class WorkShift {
    private DayOfWeek dayOfWeek;
    private Time start;
    private Time end;

    public WorkShift(String dayOfWeek, Time start, Time end) {
        this.dayOfWeek = DayOfWeek.fromString(dayOfWeek);
        this.start = start;
        this.end = end;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public Time getStart() {
        return this.start;
    }

    public Time getEnd() {
        return this.end;
    }
}

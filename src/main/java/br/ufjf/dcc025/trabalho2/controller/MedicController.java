package br.ufjf.dcc025.trabalho2.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidDateException;
import br.ufjf.dcc025.trabalho2.model.services.WorkShift;
import br.ufjf.dcc025.trabalho2.model.users.Medic;

public class MedicController {
    
    public void savesWorkShift(String dayOfWeek, String start, String end, Medic medic) throws InvalidDateException {
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
        WorkShift workShift = null;
        try {
            workShift = new WorkShift(dayOfWeek, parser.parse(start), parser.parse(end));
        }
        catch(ParseException e) {
            System.err.println("Erro ao converter a String para o tipo Date");
            e.printStackTrace();
        }
        catch(InvalidDateException e) {
            throw new InvalidDateException("Data invalida");
        }

        medic.addDisponibility(workShift);
    }
}
package br.ufjf.dcc025.trabalho2.model.users;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidDateException;
import br.ufjf.dcc025.trabalho2.model.services.WorkShift;
import br.ufjf.dcc025.trabalho2.view.UserInterface.MainFrame;
import br.ufjf.dcc025.trabalho2.view.UserInterface.MedicPanel;
import java.text.ParseException;


public class Medic extends User {    
    private Specialization specialization;
    private boolean active;
    //private List<String> agenda;
    private WorkShift[] disponibility;


    public Medic(String name, String email, String cpf, String phoneNumber, String password) {
        super(name, email, cpf, phoneNumber, password);
        this.profile = Profile.MEDICO;
        disponibility = new WorkShift[7];
    }

    public Medic(String name, Email email, CPF cpf, PhoneNumber phoneNumber, Password password) {
        super(name, email, cpf, phoneNumber, password);
        this.profile = Profile.MEDICO;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public WorkShift[] getDisponibility() {

        if (this.disponibility == null) {
            SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
            this.disponibility = new WorkShift[7];
        
            String[] dias = {
                "Segunda-feira", "Terca-feira", "Quarta-feira", 
                "Quinta-feira", "Sexta-feira", "Sabado", "Domingo"
            };

            for (int i = 0; i < 7; i++) {
                try{
                    this.disponibility[i] = new WorkShift(dias[i], parser.parse("09:00"), parser.parse("17:00"));
                }catch(ParseException e){
                    System.out.println("Putz, melou");
                }
            }
        }
        return this.disponibility;
    }
    
    public List<WorkShift> getDisponibilityAsList() {
        List<WorkShift> workshifts = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            workshifts.add(disponibility[i]);
        }
        return workshifts;
    }

    public void addDisponibility(WorkShift dayShift) {
        switch(dayShift.getDayOfWeek()) {
            case MONDAY -> {
                disponibility[0] = dayShift;
            }
            case TUESDAY -> {
                disponibility[1] = dayShift;
            }
            case WEDNESDAY -> {
                disponibility[2] = dayShift;
            }
            case THURSDAY -> {
                disponibility[3] = dayShift;
            }
            case FRIDAY -> {
                disponibility[4] = dayShift;
            }
            case SATURDAY -> {
                disponibility[5] = dayShift;
            }
            case SUNDAY -> {
                disponibility[6] = dayShift;
            }
            default -> throw new InvalidDateException("Data invalida");
        }
    }

    @Override
    public JPanel createPanel(MainFrame mainframe) {
        return new MedicPanel(mainframe, this);
    }
    
    @Override 
    public boolean getStatus(){
        return this.active;
    }
}
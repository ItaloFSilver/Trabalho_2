package br.ufjf.dcc025.trabalho2.model.users;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import br.ufjf.dcc025.trabalho2.controller.MedicController;
import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;
import br.ufjf.dcc025.trabalho2.model.services.WorkShift;
import br.ufjf.dcc025.trabalho2.view.UserInterface.MainFrame;
import br.ufjf.dcc025.trabalho2.view.UserInterface.MedicPanel;


public class Medic extends User {    
    private boolean active;
    //private List<String> agenda;
    


    public Medic(String name, String email, String cpf, String phoneNumber, String password) {
        super(name, email, cpf, phoneNumber, password);
        this.profile = Profile.MEDICO;
    }

    public Medic(String name, Email email, CPF cpf, PhoneNumber phoneNumber, Password password) {
        super(name, email, cpf, phoneNumber, password, true);
        this.profile = Profile.MEDICO;
        
    }

    public List<WorkShift> getDisponibilityAsList() {
        MedicController medic = new MedicController();
        
        return medic.loadWorkShift(this.getCPF());
    }

    @Override
    public JPanel createPanel(MainFrame mainframe) {
        return new MedicPanel(mainframe, this);
    }
    
    @Override 
    public boolean getStatus(){
        return this.active;
    }
    @Override 
    public void setStatus(boolean sts){
        this.status = sts;
    }
    
    public List<String> getFreeTime(int dds){
        List<String> horarios = new ArrayList<>();
        horarios = getDisponibilityAsList().get(dds).getFreeTime();
        
        return horarios;
    }
    

}
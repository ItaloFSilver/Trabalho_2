package br.ufjf.dcc025.trabalho2.model.users;

import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;
import br.ufjf.dcc025.trabalho2.view.UserInterface.MedicPanel;

public class Medic extends User {    
    private Specialization specialization;
    private boolean active;
    private List<String> agenda;

    public Medic(String name, String email, String cpf, String phoneNumber, String password) {
        super(name, email, cpf, phoneNumber, password);
        this.profile = Profile.MEDICO;
    }

    public Medic(String name, Email email, CPF cpf, PhoneNumber phoneNumber, Password password) {
        super(name, email, cpf, phoneNumber, password);
        this.profile = Profile.MEDICO;
    }

    public Specialization getSpecialization() {
        return specialization;
    }
    
    public List<String> getDisponibility(){
        return agenda;
    }

    @Override
    public JPanel createPanel(br.ufjf.dcc025.trabalho2.view.UserInterface.MainFrame mainframe) {
        return new MedicPanel(mainframe, this);
    }
    
    @Override 
    public boolean getStatus(){
        return this.active;
    }
}
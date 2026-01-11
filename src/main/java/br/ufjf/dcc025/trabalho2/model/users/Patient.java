package br.ufjf.dcc025.trabalho2.model.users;

import javax.swing.JPanel;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;
import br.ufjf.dcc025.trabalho2.view.UserInterface.PatientPanel;

public class Patient extends User {
    public boolean hospitalized;
    
    public Patient(String name, String email, String cpf, String phoneNumber, String password) {
        super(name, email, cpf, phoneNumber, password);
        this.profile = Profile.PACIENTE;
    }

    public Patient(String name, Email email, CPF cpf, PhoneNumber phoneNumber, Password password, boolean stat) {
        super(name, email, cpf, phoneNumber, password, stat);
        this.profile = Profile.PACIENTE;
    }

    @Override
    public JPanel createPanel(br.ufjf.dcc025.trabalho2.view.UserInterface.MainFrame mainframe) {
        return new PatientPanel(mainframe, this);
    }
    
    @Override
    public boolean getStatus(){
        return this.status;
    }
    @Override
    public void setStatus(boolean bool){
        this.status = bool;
    }
    
    public void setHospitalized(boolean bool){
        this.hospitalized = bool;
    }
    
    public boolean isHospitalized(){
        return this.hospitalized;
    }
}
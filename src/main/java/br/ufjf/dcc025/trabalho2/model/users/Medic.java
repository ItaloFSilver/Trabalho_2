package br.ufjf.dcc025.trabalho2.model.users;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;
import java.util.Date;
import java.util.List;

public class Medic extends User {    
    private Specialization specialization;
    //private boolean active;
    private List<Date> datasDisponiveis;

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
    
}
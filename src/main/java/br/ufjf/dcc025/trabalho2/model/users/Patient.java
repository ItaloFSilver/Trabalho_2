package br.ufjf.dcc025.trabalho2.model.users;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;

public class Patient extends User {
    boolean hospitalized;
    boolean visitable;

    public Patient(String name, String email, String cpf, String phoneNumber, String password) {
        super(name, email, cpf, phoneNumber, password);
        this.profile = Profile.PACIENTE;
    }

    public Patient(String name, Email email, CPF cpf, PhoneNumber phoneNumber, Password password) {
        super(name, email, cpf, phoneNumber, password);
        this.profile = Profile.PACIENTE;
    }
}
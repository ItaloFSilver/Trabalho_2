package br.ufjf.dcc025.trabalho2.model.users;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;

public class Secretary extends User {
    public Secretary(String name, String email, String cpf, String phoneNumber, String password) {
        super(name, email, cpf, phoneNumber, password);
        this.profile = Profile.SECRETARIO;
    } 

    public Secretary(String name, Email email, CPF cpf, PhoneNumber phoneNumber, Password password) {
        super(name, email, cpf, phoneNumber, password);
        this.profile = Profile.SECRETARIO;
    }
}

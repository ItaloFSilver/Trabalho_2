package br.ufjf.dcc025.trabalho2.model.users;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.Telefone;

public class Secretary extends User {

    public Secretary(String name, String email, String cpf, String telefone, String password) {
        super(name, email, cpf, telefone, password);
    } 

    public Secretary(String name, Email email, CPF cpf, Telefone telefone, Password password) {
        super(name, email, cpf, telefone, password);
    }
}

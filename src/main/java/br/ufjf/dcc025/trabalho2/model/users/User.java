package br.ufjf.dcc025.trabalho2.model.users;

import java.util.UUID;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.Telefone;

public class User {
    
    protected UUID id = UUID.randomUUID();
    protected String name;
    protected Email email;
    protected CPF cpf;
    protected Password password;
    protected Telefone telefone;

    /**
    * @throws InvalidEmailException se o email for invalido
    * @throws InvalidCPFException se o CPF for invalido
    * @throws InvalidTelefoneException se o telefone for invalido
    * @throws InvalidPasswordException se a senha for invalida
    */
    public User(String name, String email, String cpf, String telefone, String password) {
        this.name = name;
        this.email = new Email(email);
        this.cpf = new CPF(cpf);
        this.telefone = new Telefone(telefone);
        this.password = new Password(password);
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Email getEmail() {
        return this.email;
    }

    public Password getPassword() {
        return this.password;
    }
    
    public CPF getCPF() {
        return this.cpf;
    }

    public Telefone getTelefone() {
        return this.telefone;
    }
}
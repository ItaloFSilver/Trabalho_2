package br.ufjf.dcc025.trabalho2.model.users;

import javax.swing.JPanel;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;
import br.ufjf.dcc025.trabalho2.view.UserInterface.MainFrame;

public abstract class User {
    
    protected Profile profile;
    protected String name;
    protected Email email;
    protected CPF cpf;
    protected Password password;
    protected PhoneNumber phoneNumber;

    /**
    * @throws InvalidEmailException se o email for invalido
    * @throws InvalidCPFException se o CPF for invalido
    * @throws InvalidphoneNumberException se o phoneNumber for invalido
    * @throws InvalidPasswordException se a senha for invalida
    */
    public User(String name, String email, String cpf, String phoneNumber, String password) {
        this.name = name;
        this.email = new Email(email);
        this.cpf = new CPF(cpf);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.password = new Password(password);
    }

    public User(String name, Email email, CPF cpf, PhoneNumber phoneNumber, Password password) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public abstract JPanel createPanel(MainFrame mainframe);

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email.getEmail();
    }

    public String getPassword() {
        return this.password.getPassword();
    }
    
    public CPF getCPF() {
        return this.cpf;
    }

    public PhoneNumber getphoneNumber() {
        return this.phoneNumber;
    }

    public Profile getProfile() {
        return this.profile;
    }
}
/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
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
    protected boolean status;

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

    public User(String name, Email email, CPF cpf, PhoneNumber phoneNumber, Password password, boolean status) {
       this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.status = status;
    }

    //cria o painel do usuário
    public abstract JPanel createPanel(MainFrame mainframe);

    //retorna o nome do usuário
    public String getName() {
        return this.name;
    }

    //retorna o email como Email
    public Email getFormatEmail(){
        return this.email;
    }
    
    //retorna o email como String
    public String getEmail() {
        return this.email.getEmail();
    }
    
    //retorna a senha como Password
    public Password getFormatPassword(){
        return this.password;
    }
    
    //retorna a senha como String
    public String getPassword() {
        return this.password.getPassword();
    }
    
    //retorna o CPF do user
    public CPF getCPF() {
        return this.cpf;
    }

    //retorna o phoneNumber como PhoneNumber. ex: (##)#####-####
    public PhoneNumber getphoneNumber() {
        return this.phoneNumber;
    }

    //retorna o profile como enum
    public Profile getProfile() {
        return this.profile;
    }
    
     //retorna se o paciente pode ou não receber visitas
    public boolean getStatus(){
        return this.status;
    }
    
    //define se o paciente poderá receber visitas
    public void setStatus(boolean bool){
        this.status = bool;
    }
}
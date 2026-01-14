/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.controller;

import java.util.List;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidCPFException;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidEmailException;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidPasswordException;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidRegisterException;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidphoneNumberException;
import br.ufjf.dcc025.trabalho2.model.repository.UserRepository;
import br.ufjf.dcc025.trabalho2.model.users.Profile;
import br.ufjf.dcc025.trabalho2.model.users.User;

public class RegisterController {
    private final String regex = "^[A-Za-zÀ-ú']+(\\s[A-Za-zÀ-ú']+)+$";
    
    //método para registrar usuário com base nos dados passados, caso o CPF ou o email já tenham sido cadastrados, retorna erro
    public void registerUser(String name, String email, String cpf, String phoneNumber, String password, Profile userType, boolean stat) throws InvalidRegisterException {

        String nAme = name;
        Email emailObj;
        CPF cpfObj;
        PhoneNumber phoneNumberObj;
        Password passwordObj;
        UserRepository repository = new UserRepository();
        List<User> users = repository.listAllUsers();
        try {
            if(!nAme.matches(regex))
                throw new InvalidEmailException("Nome inválido");
            emailObj = new Email(email);
            cpfObj = new CPF(cpf);
            phoneNumberObj = new PhoneNumber(phoneNumber);
            passwordObj = new Password(password);
            for(User u : users){
                if(cpfObj.equals(u.getCPF())){
                    throw new InvalidCPFException("CPF já cadastrado");
                }
                if(emailObj.toString().equals(u.getEmail()))
                    throw new InvalidEmailException("E-mail já cadastrado");
            }
        } 
        catch (InvalidEmailException | InvalidCPFException | InvalidRegisterException | InvalidPasswordException | InvalidphoneNumberException e) {
            throw new InvalidRegisterException(e.getMessage());
        }

        repository.saveUser(name, emailObj, cpfObj, null, phoneNumberObj, passwordObj, userType, stat);
    }
}
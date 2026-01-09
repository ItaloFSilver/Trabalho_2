package br.ufjf.dcc025.trabalho2.controller;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidCPFException;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidEmailException;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidPasswordException;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidRegisterException;
import br.ufjf.dcc025.trabalho2.model.repository.UserRepository;
import br.ufjf.dcc025.trabalho2.model.users.Profile;

public class RegisterController {
    
    public void registerUser(String name, String email, String cpf, String phoneNumber, String password, Profile userType) throws InvalidRegisterException {

        Email emailObj;
        CPF cpfObj;
        PhoneNumber phoneNumberObj;
        Password passwordObj;

        try {
            emailObj = new Email(email);
            cpfObj = new CPF(cpf);
            phoneNumberObj = new PhoneNumber(phoneNumber);
            passwordObj = new Password(password);
        } 
        catch (InvalidEmailException | InvalidCPFException | InvalidRegisterException | InvalidPasswordException e) {
            throw new InvalidRegisterException(e.getMessage());
        }

        new UserRepository().saveUser(name, emailObj, cpfObj, null, phoneNumberObj, passwordObj, userType);
    }
}
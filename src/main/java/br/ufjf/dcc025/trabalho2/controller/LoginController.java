package br.ufjf.dcc025.trabalho2.controller;

import java.util.List;

import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidEmailException;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidLoginException;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidPasswordException;
import br.ufjf.dcc025.trabalho2.model.repository.UserRepository;
import br.ufjf.dcc025.trabalho2.model.users.User;

public class LoginController {
    
    public User login(String email, String password) throws InvalidLoginException {

        List<User> users = new UserRepository().listAllUsers();
        Email emailObj;
        Password passwordObj;

        try {
            emailObj = new Email(email);
            passwordObj = new Password(password);
        } 
        catch (InvalidEmailException | InvalidPasswordException e) {
            throw new InvalidLoginException("Email ou senha invalidos.");
        }

        for(User user : users) {
            if(user.getEmail().equals(emailObj.getEmail()) && user.getPassword().equals(passwordObj.getPassword())){ 
                return user;
            } else {
            }  
        }

        throw new InvalidLoginException("Email ou senha invalidos.");
    }
}

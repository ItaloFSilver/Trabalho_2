package br.ufjf.dcc025.trabalho2.controller;

import java.util.List;

import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.error.InvalidLoginException;
import br.ufjf.dcc025.trabalho2.model.repository.UserRepository;
import br.ufjf.dcc025.trabalho2.model.users.User;

public class LoginController {
    
    public User Login(String email, String password) throws InvalidLoginException {

        List<User> users = new UserRepository().getAllUsers();

        for(User user : users) {
            if(user.getEmail().equals(new Email(email)) && user.getPassword().equals(new Password(password))){
                return user; 
            }  
        }

        throw new InvalidLoginException("Email ou senha invalidos.");
    }
}

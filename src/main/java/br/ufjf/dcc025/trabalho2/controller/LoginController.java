package br.ufjf.dcc025.trabalho2.controller;

import java.util.List;

import br.ufjf.dcc025.trabalho2.model.Credentials.Email;
import br.ufjf.dcc025.trabalho2.model.Credentials.Password;
import br.ufjf.dcc025.trabalho2.model.Error.InvalidLoginException;
import br.ufjf.dcc025.trabalho2.model.Users.User;
import br.ufjf.dcc025.trabalho2.model.repository.UserRepository;

public class LoginController {
    
    public User Login(String email, String password) throws InvalidLoginException {

        List<User> users = new UserRepository().getAllUsers();

        for(User user : users){
            if(user.getEmail().equals(new Email(email)) && user.getPassword().equals(new Password(password))){
                return user; 
            }  
        }

        throw new InvalidLoginException("Email ou senha invalidos.");
    }
}

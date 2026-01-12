/*Arthur de Souza Marques - 202435015 */
/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.controller;

import java.util.List;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidRemoveException;
import br.ufjf.dcc025.trabalho2.model.repository.MedicRepository;
import br.ufjf.dcc025.trabalho2.model.repository.PatientRepository;
import br.ufjf.dcc025.trabalho2.model.repository.UserRepository;
import br.ufjf.dcc025.trabalho2.model.users.User;

public class SecretaryController {
    
    public List<User> listAllUsers() {
        return new UserRepository().listAllUsers();
    }
    public List<User> listPatients(){
        return new PatientRepository().listAll();
    }
    
    public List<User> listMedics(){
        return new MedicRepository().listAll();
    }
    
    public void removeUserByCPF(String cpf) throws InvalidRemoveException {
        UserRepository userRepo = new UserRepository();
        User user = userRepo.findByCPF(cpf);
        try {
            userRepo.removeUser(user);
        } catch (InvalidRemoveException e) {
            throw new InvalidRemoveException("Erro ao remover usuario: " + e.getMessage());
        }
    }
    public User findUserByCPF(String cpf){
        UserRepository userRepo = new UserRepository();
        User user = userRepo.findByCPF(cpf);
        return user;
    }
    
    public void setStatus(CPF cpf, boolean bool){
        UserRepository userRepo = new UserRepository();
        User user = userRepo.findByCPF(cpf.toString());
        
        user.setStatus(bool);
        
        userRepo.saveUser(user, bool);
    }
    
    public boolean checkStatus(CPF cpf){
        UserRepository userRepo = new UserRepository();
        User user = userRepo.findByCPF(cpf.toString());
        
        return user.getStatus();
    }
}

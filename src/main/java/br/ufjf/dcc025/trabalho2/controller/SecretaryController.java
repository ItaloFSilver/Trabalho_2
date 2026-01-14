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
    
    //retorna uma lista com todos os usuários para que a secretária faça a gestão do sistema
    public List<User> listAllUsers() {
        return new UserRepository().listAllUsers();
    }
    
    //retorna uma lista apenas com os pacientes para fazer o agendamento
    public List<User> listPatients(){
        return new PatientRepository().listAll();
    }
    
    //retorna uma lista apenas com os médicos para fazer o agendamento
    public List<User> listMedics(){
        return new MedicRepository().listAll();
    }
    
    //remove o usuário do sistema de acordo com o CPF, que é único para cada usuário
    public void removeUserByCPF(String cpf) throws InvalidRemoveException {
        UserRepository userRepo = new UserRepository();
        User user = userRepo.findByCPF(cpf);
        try {
            userRepo.removeUser(user);
        } catch (InvalidRemoveException e) {
            throw new InvalidRemoveException("Erro ao remover usuario: " + e.getMessage());
        }
    }
    
    //encontra o usuário com base no CPF informado
    public User findUserByCPF(String cpf){
        UserRepository userRepo = new UserRepository();
        User user = userRepo.findByCPF(cpf);
        return user;
    }
    
    //Secretária define se o paciente está apto ou não a receber visitas
    public void setStatus(CPF cpf, boolean bool){
        UserRepository userRepo = new UserRepository();
        User user = userRepo.findByCPF(cpf.toString());
        
        user.setStatus(bool);
        
        userRepo.saveUser(user, bool);
    }
    
    //Confere o status atual do paciente para listá-lo como apto ou não para visitas
    public boolean checkStatus(CPF cpf){
        UserRepository userRepo = new UserRepository();
        User user = userRepo.findByCPF(cpf.toString());
        
        return user.getStatus();
    }
}

package br.ufjf.dcc025.trabalho2.controller;

import java.util.List;

import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidRemoveException;
import br.ufjf.dcc025.trabalho2.model.repository.MedicRepository;
import br.ufjf.dcc025.trabalho2.model.repository.PatientRepository;
import br.ufjf.dcc025.trabalho2.model.repository.UserRepository;
import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.Patient;
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
}

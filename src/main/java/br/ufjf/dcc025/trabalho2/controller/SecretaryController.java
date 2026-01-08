package br.ufjf.dcc025.trabalho2.controller;

import java.util.List;

import br.ufjf.dcc025.trabalho2.model.repository.MedicRepository;
import br.ufjf.dcc025.trabalho2.model.repository.PatientRepository;
import br.ufjf.dcc025.trabalho2.model.repository.UserRepository;
import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.Patient;
import br.ufjf.dcc025.trabalho2.model.users.Profile;
import br.ufjf.dcc025.trabalho2.model.users.User;

public class SecretaryController {
        
    public List<User> listAllUsers() {
        return new UserRepository().listAllUsers();
    }

    public void removeUser(User user) {
        switch (user.getProfile()) {
            case Profile.MEDICO -> {
                new MedicRepository().remove((Medic) user);
            }
            case Profile.PACIENTE -> {
                new PatientRepository().remove((Patient) user);
            }
            default -> {
                throw new AssertionError();
            }
        }
    }
    public void removePatient(Patient patient) {
        new PatientRepository().remove(patient);
    }

    public void removeByCPF(String cpf) {
        User user = new UserRepository().findByCPF(cpf);
        if (user != null) {
            removeUser(user);
                
            }
        }
    
}

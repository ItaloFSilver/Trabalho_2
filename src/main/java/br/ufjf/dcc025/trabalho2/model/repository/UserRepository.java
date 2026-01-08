package br.ufjf.dcc025.trabalho2.model.repository;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.dcc025.trabalho2.model.users.User;

public class UserRepository {

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        
        users.addAll(new SecretaryRepository().listAll());
        users.addAll(new PatientRepository().listAll());
        users.addAll(new MedicRepository().listAll());

        return users;
    }
}

package br.ufjf.dcc025.trabalho2.model.repository;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.users.User;

public class UserRepository {

    public User findByCPF(String cpf) {
        List<User> users = listAllUsers();
        for (User user : users) {
            if (user.getCPF().equals(new CPF(cpf))) {
                return user;
            }
        }
        return null;
    }

    public List<User> listAllUsers() {
        List<User> users = new ArrayList<>();
        
        users.addAll(new SecretaryRepository().listAll());
        users.addAll(new PatientRepository().listAll());
        users.addAll(new MedicRepository().listAll());

        return users;
    }
}

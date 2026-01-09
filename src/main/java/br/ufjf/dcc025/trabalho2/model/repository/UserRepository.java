package br.ufjf.dcc025.trabalho2.model.repository;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidRemoveException;
import br.ufjf.dcc025.trabalho2.model.users.User;

public class UserRepository {

    private List<BaseRepository<? extends User>> repositories = List.of(
        new SecretaryRepository(),
        new PatientRepository(),
        new MedicRepository()
    );

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

    public void removeUser(User user) throws InvalidRemoveException{
        for(BaseRepository<? extends User> repo : repositories) {
            repo.remove(user);
        }
    }
}

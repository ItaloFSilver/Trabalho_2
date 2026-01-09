package br.ufjf.dcc025.trabalho2.model.repository;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidRemoveException;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidSaveException;
import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.Patient;
import br.ufjf.dcc025.trabalho2.model.users.Profile;
import br.ufjf.dcc025.trabalho2.model.users.Secretary;
import br.ufjf.dcc025.trabalho2.model.users.User;

public class UserRepository {

    private List<BaseRepository<? extends User>> repositories = List.of(
        new SecretaryRepository(),
        new PatientRepository(),
        new MedicRepository()
    );

    public void saveUser(String name, Email email, CPF cpf, User user, PhoneNumber number, Password password, Profile profile) throws InvalidSaveException {
        switch(profile) {
            case PACIENTE -> {
                Patient patient = new Patient(name, email, cpf, number, password);
                PatientRepository patientRepo = new PatientRepository();
                patientRepo.save(patient);
            }
            case MEDICO -> {
                Medic medic = new Medic(name, email, cpf, number, password);
                MedicRepository medicRepo = new MedicRepository();
                medicRepo.save(medic);
            }
            case SECRETARIO -> {
                Secretary secretary = new Secretary(name, email, cpf, number, password);
                SecretaryRepository secretaryRepo = new SecretaryRepository();
                secretaryRepo.save(secretary);
            }
            default -> throw new InvalidSaveException("Tipo de usuário inválido.");
        }
    }

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
            try {
                repo.remove(user);
            } 
            catch (InvalidRemoveException e) {
                throw new InvalidRemoveException(e.getMessage());
            }
        }
    }
}

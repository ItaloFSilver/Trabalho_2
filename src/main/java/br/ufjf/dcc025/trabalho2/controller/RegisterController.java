package br.ufjf.dcc025.trabalho2.controller;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.Telefone;
import br.ufjf.dcc025.trabalho2.model.error.InvalidCPFException;
import br.ufjf.dcc025.trabalho2.model.error.InvalidEmailException;
import br.ufjf.dcc025.trabalho2.model.error.InvalidPasswordException;
import br.ufjf.dcc025.trabalho2.model.error.InvalidRegisterException;
import br.ufjf.dcc025.trabalho2.model.repository.MedicRepository;
import br.ufjf.dcc025.trabalho2.model.repository.PatientRepository;
import br.ufjf.dcc025.trabalho2.model.repository.SecretaryRepository;
import br.ufjf.dcc025.trabalho2.model.users.Medic;
import br.ufjf.dcc025.trabalho2.model.users.Patient;
import br.ufjf.dcc025.trabalho2.model.users.Secretary;

public class RegisterController {
    
    public void registerUser(String name, String email, String cpf, String telefone, String password, String userType) throws InvalidRegisterException {

        Email emailObj;
        CPF cpfObj;
        Telefone telefoneObj;
        Password passwordObj;

        try {
            emailObj = new Email(email);
            cpfObj = new CPF(cpf);
            telefoneObj = new Telefone(telefone);
            passwordObj = new Password(password);
        } 
        catch (InvalidEmailException | InvalidCPFException | InvalidRegisterException | InvalidPasswordException e) {
            throw new InvalidRegisterException(e.getMessage());
        }

        switch (userType) {
            case "Paciente":
                Patient patient = new Patient(name, emailObj, cpfObj, telefoneObj, passwordObj);
                new PatientRepository().save(patient);
                break;
            case "Médico":
                Medic medic = new Medic(name, emailObj, cpfObj, telefoneObj, passwordObj);
                new MedicRepository().save(medic);
                break;
            case "Secretário":
                Secretary secretary = new Secretary(name, emailObj, cpfObj, telefoneObj, passwordObj);
                new SecretaryRepository().save(secretary);
                break;
            default:
                throw new InvalidRegisterException("Tipo de usuário inválido.");
        }


    }

}

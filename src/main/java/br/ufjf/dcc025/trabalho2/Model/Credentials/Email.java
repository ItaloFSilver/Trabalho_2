package br.ufjf.dcc025.trabalho2.Model.Credentials;

import br.ufjf.dcc025.trabalho2.Model.Error.InvalidEmailException;

public class Email {

    private final String email;
    
    private final String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public Email(String email) throws InvalidEmailException{
        if(!validateEmail(email)) {
            throw  new InvalidEmailException("Email invalido");
        }
        else {
            this.email = email;
        }
    }

    private boolean validateEmail(String email) {
        return email.matches(regex);
    }
}

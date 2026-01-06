package ufjf.dcc025.trabalho2.Credentials;

import ufjf.dcc025.trabalho2.Error.InvalidEmailException;

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
        if(!email.matches(regex)) {
            return false;
        }
        return true;
    }
}

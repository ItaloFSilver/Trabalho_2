package br.ufjf.dcc025.trabalho2.model.Credentials;

import br.ufjf.dcc025.trabalho2.model.Error.InvalidEmailException;

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

    private String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Email other = (Email) obj;
        return email.equals(other.getEmail());
    }
}

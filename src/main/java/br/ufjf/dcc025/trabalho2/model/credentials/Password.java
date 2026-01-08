package br.ufjf.dcc025.trabalho2.model.credentials;

import br.ufjf.dcc025.trabalho2.model.error.InvalidPasswordException;

public class Password {
    private final String password;

    public Password(String password) throws InvalidPasswordException{
        if(!validatePassword(password)) {
            throw new InvalidPasswordException("Senha precisa ter no minimo 8 caracteres");
        }
        else {
            this.password = password;
        }
    }

    private boolean validatePassword(String password) {
        return password.length() >= 8;
    }

    public String getPassword() {
        return password;
    }
    
    @Override
    public int hashCode() {
        return password.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Password other = (Password) obj;
        return password.equals(other.getPassword());
    }
}

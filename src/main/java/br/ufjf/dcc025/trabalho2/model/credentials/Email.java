/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.model.credentials;

import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidEmailException;

public class Email {

    private final String email;
    
    private final static transient String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public Email(String email) throws InvalidEmailException {
        if(!validateEmail(email)) {
            throw  new InvalidEmailException("Email invalido");
        }
        else {
            this.email = email;
        }
    }
    
    //valida se o email possui domínio
    private boolean validateEmail(String email) {
        return email.matches(regex);
    }

    //retorna o email do usuário
    public String getEmail() {
        return email;
    }
    @Override
    public String toString(){
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

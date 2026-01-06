package ufjf.dcc025.trabalho2.Credentials;

import ufjf.dcc025.trabalho2.Error.InvalidTelefoneException;

public class Telefone {

    private final String telefone;

    private static final String regex = "^(\\(?\\d{2}\\)?\\s?)?9\\d{4}-?\\d{4}$";

    public Telefone(String telefone) throws InvalidTelefoneException{
        if(!validateTelefone(telefone)) {
            throw new InvalidTelefoneException("Telefone invalido");
        }
        this.telefone = telefone;
    }

    private boolean validateTelefone(String telefone) {
        if(!telefone.matches(regex)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + telefone.charAt(0) + telefone.charAt(1) + ")" + telefone.charAt(2) + telefone.charAt(3) + 
                telefone.charAt(4) + telefone.charAt(5) + telefone.charAt(6) + "-" + telefone.charAt(7) + 
                telefone.charAt(8) + telefone.charAt(9) + telefone.charAt(10);
    }

}

package br.ufjf.dcc025.trabalho2.model.credentials;

import br.ufjf.dcc025.trabalho2.model.error.InvalidTelefoneException;

public class Telefone {

    private final String telefone;

    private static transient final String regex = "^(\\(?\\d{2}\\)?\\s?)?9\\d{4}-?\\d{4}$";

    public Telefone(String telefone) throws InvalidTelefoneException {
        telefone = this.normalize(telefone);

        if(!validateTelefone(telefone)) {
            throw new InvalidTelefoneException("Telefone invalido");
        }
        this.telefone = telefone;
    }

    private boolean validateTelefone(String telefone) {
        return telefone.matches(regex);
    }

    private String normalize(String telefone) {
        return telefone.replaceAll("[^0-9]", ""); // Remove tudo que não for número
    }

    @Override
    public String toString() {
        return "(" + telefone.charAt(0) + telefone.charAt(1) + ")" + telefone.charAt(2) + telefone.charAt(3) + 
                telefone.charAt(4) + telefone.charAt(5) + telefone.charAt(6) + "-" + telefone.charAt(7) + 
                telefone.charAt(8) + telefone.charAt(9) + telefone.charAt(10);
    }
}
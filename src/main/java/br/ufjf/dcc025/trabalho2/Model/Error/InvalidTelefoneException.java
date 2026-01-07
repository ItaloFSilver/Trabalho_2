package br.ufjf.dcc025.trabalho2.Model.Error;

public class InvalidTelefoneException extends RuntimeException {
    public InvalidTelefoneException(String message) {
        super(message);
    }
}

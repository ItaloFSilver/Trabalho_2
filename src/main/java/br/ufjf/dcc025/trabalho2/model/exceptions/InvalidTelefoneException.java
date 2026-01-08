package br.ufjf.dcc025.trabalho2.model.exceptions;

public class InvalidTelefoneException extends RuntimeException {
    public InvalidTelefoneException(String message) {
        super(message);
    }
}

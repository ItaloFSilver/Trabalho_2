package br.ufjf.dcc025.trabalho2.model.exceptions;

public class InvalidRegisterException extends RuntimeException {
    public InvalidRegisterException(String message) {
        super(message);
    }
}

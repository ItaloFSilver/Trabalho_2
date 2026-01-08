package br.ufjf.dcc025.trabalho2.model.error;

public class InvalidRegisterException extends RuntimeException {
    public InvalidRegisterException(String message) {
        super(message);
    }
}

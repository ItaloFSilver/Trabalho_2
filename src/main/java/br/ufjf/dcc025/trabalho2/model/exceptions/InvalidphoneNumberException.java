package br.ufjf.dcc025.trabalho2.model.exceptions;

public class InvalidphoneNumberException extends RuntimeException {
    public InvalidphoneNumberException(String message) {
        super(message);
    }
}

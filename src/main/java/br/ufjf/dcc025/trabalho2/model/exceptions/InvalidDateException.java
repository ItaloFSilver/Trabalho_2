package br.ufjf.dcc025.trabalho2.model.exceptions;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String message) {
        super(message);
    }
}

package br.ufjf.dcc025.trabalho2.model.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String mensagem) {
        super(mensagem);
    }
}
package br.ufjf.dcc025.trabalho2.model.Error;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String mensagem) {
        super(mensagem);
    }
}
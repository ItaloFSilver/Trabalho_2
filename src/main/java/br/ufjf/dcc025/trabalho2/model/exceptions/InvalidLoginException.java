package br.ufjf.dcc025.trabalho2.model.exceptions;


public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String mensagem){
        super(mensagem);
    }
}

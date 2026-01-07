package br.ufjf.dcc025.trabalho2.model.Error;


public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String mensagem){
        super(mensagem);
    }
}

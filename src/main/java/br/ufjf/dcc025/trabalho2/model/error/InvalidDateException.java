package br.ufjf.dcc025.trabalho2.model.error;

public class InvalidDateException extends RuntimeException{
    public InvalidDateException(String mensagem){
        super(mensagem);
    }
}

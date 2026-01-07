package br.ufjf.dcc025.trabalho2.model.Error;


public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String mensagem){
        super(mensagem);
    }
}

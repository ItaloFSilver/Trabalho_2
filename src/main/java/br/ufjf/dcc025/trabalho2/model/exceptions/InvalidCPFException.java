package br.ufjf.dcc025.trabalho2.model.exceptions;


public class InvalidCPFException extends RuntimeException {
    public InvalidCPFException(String mensagem){
        super(mensagem);
    }
}

package br.ufjf.dcc025.trabalho2.model.Error;


public class InvalidCPFException extends RuntimeException {
    public InvalidCPFException(String mensagem){
        super(mensagem);
    }
}

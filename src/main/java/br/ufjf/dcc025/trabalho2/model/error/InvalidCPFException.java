package br.ufjf.dcc025.trabalho2.model.error;


public class InvalidCPFException extends RuntimeException {
    public InvalidCPFException(String mensagem){
        super(mensagem);
    }
}

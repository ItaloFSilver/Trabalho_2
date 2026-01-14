/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.model.exceptions;

public class InvalidSaveException extends RuntimeException {
    public InvalidSaveException(String message) {
        super(message);
    }   
}

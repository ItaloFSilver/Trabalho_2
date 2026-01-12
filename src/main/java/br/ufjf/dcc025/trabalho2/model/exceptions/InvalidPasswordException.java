/*Arthur de Souza Marques - 202435015 */
/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.model.exceptions;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String mensagem) {
        super(mensagem);
    }
}
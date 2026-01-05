/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufjf.dcc025.trabalho2.Error;

/**
 *
 * @author Italo
 */
public class InvalidCPFException extends RuntimeException {
    public InvalidCPFException(String mensagem){
        super(mensagem);
    }
}

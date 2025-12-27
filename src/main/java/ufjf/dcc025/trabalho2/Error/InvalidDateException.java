/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ufjf.dcc025.trabalho2.Error;
import java.lang.Exception;
/**
 *
 * @author Ircasa
 */
public class InvalidDateException extends Exception{
    public InvalidDateException(String mensagem){
        super(mensagem);
    }
}

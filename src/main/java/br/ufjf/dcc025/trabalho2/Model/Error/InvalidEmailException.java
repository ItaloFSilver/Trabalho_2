/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.trabalho2.Model.Error;


public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String mensagem){
        super(mensagem);
    }
}

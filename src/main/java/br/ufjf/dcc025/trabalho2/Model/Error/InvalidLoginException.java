/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.trabalho2.Model.Error;


public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String mensagem){
        super(mensagem);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc025.trabalho2.Model.Credentials;
import br.ufjf.dcc025.trabalho2.Model.Error.InvalidDateException;
/**
 *
 * @author Ircasa
 */
public class Date {
    public int day;
    public int month;
    public int year;
    
    InvalidDateException d = new InvalidDateException("invalid date informed");
    
    public Date(int dd, int mm, int yy) throws InvalidDateException{
        if(mm < 1 || mm > 12){
            throw d;
        }
        else if(dd < 1 || dd > 31)
            throw d;
        else if(mm == 2 && dd > 28)
            throw d;
        day = dd;
        month = mm;
        year = yy;
    } 
}

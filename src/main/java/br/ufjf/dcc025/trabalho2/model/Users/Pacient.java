package br.ufjf.dcc025.trabalho2.model.Users;

public class Pacient extends User{
    
    public Pacient(String name, String email, String cpf, String phoneNmb){
        super(name, email, cpf, phoneNmb);
    }
    
    @Override
    public User loginUser(){
        return this;
    }
}

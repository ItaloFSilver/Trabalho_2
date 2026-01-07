package br.ufjf.dcc025.trabalho2.model.Users;

public class Patient extends User{
    
    public Patient(String name, String email, String cpf){
        super(name, email, cpf);
    }
    
    @Override
    public User loginUser(){
        return this;
    }
}

package br.ufjf.dcc025.trabalho2.model.Users;

public class Medic extends User{    

    public Medic(String name, String email, String cpf) {
        super(name, email, cpf);
    }

    protected void saveUser() {

    }

    public void setAppointment() {

    }
    
@Override
    public User loginUser(){
        return this;
    }
}

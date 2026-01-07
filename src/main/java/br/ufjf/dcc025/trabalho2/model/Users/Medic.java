package br.ufjf.dcc025.trabalho2.model.Users;

public class Medic extends User{    

    public Medic(String name, String email, String cpf, String phoneNmb) {
        super(name, email, cpf, phoneNmb);
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

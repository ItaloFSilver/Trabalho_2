package br.ufjf.dcc025.trabalho2.model.Users;

public class Secretary extends User{
    
    public Secretary(String nome, String email, String cpf, String phoneNmb) {
        super(nome, email, cpf, phoneNmb);
    }

    @Override
    public User loginUser() {
        return this;
    }
    
}

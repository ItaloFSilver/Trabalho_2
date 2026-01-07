package br.ufjf.dcc025.trabalho2.model.Users;

public class Secretary extends User{
    
    public Secretary() {}

    public Secretary(String nome, String email, String cpf) {
        super(nome, email, cpf);
    }

    @Override
    public User loginUser() {
        return this;
    }
    
}

package br.ufjf.dcc025.trabalho2.Model.Users;

import br.ufjf.dcc025.trabalho2.Model.Credentials.*;

public abstract class User {

    protected String id;
    protected String name;
    protected Email email;
    protected CPF cpf;

    public User() {}
    
    public User(String name, String email, String cpf) {
        this.name = name;
        this.email = new Email(email);
        this.cpf = new CPF(cpf);
    }

    // @return email do usuário
    public String getEmail() {
        String tempEmail = this.email;
        return tempEmail;
    }

    // @return cpf do usuário
    public String getCpf() {
        String tempCpf = this.cpf;
        return tempCpf;
    }

    // @return nome do usuário
    public String getName() {
        String tempName = this.name;
        return tempName;
    }

    // Adiciona o usuário no arquivo json correspondente ao seu tipo de login
    protected abstract User loginUser();
}

package ufjf.dcc025.trabalho2.Users;

public abstract class User {

    protected String id;
    protected String name;
    protected String email;
    protected String cpf;

    public User() {}
    
    public User(String name, String email, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    // @return email do usuário
    public String getEmail() {
        return this.email;
    }

    // @return cpf do usuário
    public String getCpf() {
        return this.cpf;
    }

    // @return nome do usuário
    public String getName() {
        return this.name;
    }

    // Adiciona o usuário no arquivo json correspondente ao seu tipo de login
    protected abstract User loginUser();
}

package ufjf.dcc025.trabalho2.Users;

public abstract class User {
    protected String name;
    protected String email;
    protected String cpf;

    public User() {}

    public User(String name, String email, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getName() {
        return this.name;
    }

    protected abstract void writeJson();
}

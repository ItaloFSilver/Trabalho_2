package ufjf.dcc025.trabalho2.Users;

public class Secretary extends User{
    
    public Secretary() {}

    public Secretary(String nome, String email, String cpf) {
        super(nome, email, cpf);
    }

    @Override
    public User loginUser() {
        return this;
    }
    
    public User registerNewPatient(String name, String cpf, String email){
        return new Patient(name, email, cpf);
    }
}

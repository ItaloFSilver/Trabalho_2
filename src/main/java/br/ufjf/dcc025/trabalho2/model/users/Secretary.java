/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.model.users;

import javax.swing.JPanel;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;
import br.ufjf.dcc025.trabalho2.view.UserInterface.MainFrame;
import br.ufjf.dcc025.trabalho2.view.UserInterface.SecretaryPanel;

public class Secretary extends User {
    public Secretary(String name, String email, String cpf, String phoneNumber, String password) {
        super(name, email, cpf, phoneNumber, password);
        this.profile = Profile.SECRETARIO;
    } 

    public Secretary(String name, Email email, CPF cpf, PhoneNumber phoneNumber, Password password) {
        super(name, email, cpf, phoneNumber, password, true);
        this.profile = Profile.SECRETARIO;
    }

    //cria o painel de visualização do(a) secretário(a)
    @Override
    public JPanel createPanel(MainFrame mainframe) {
        return new SecretaryPanel(mainframe, this);
    }
    
}

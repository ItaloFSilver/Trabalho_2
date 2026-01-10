package br.ufjf.dcc025.trabalho2.view.UserInterface;

import br.ufjf.dcc025.trabalho2.model.users.User;


public class MedicPanel extends UserPanel {
    
    public MedicPanel(MainFrame main) {
        super(main);
        
        this.tabbedPane.addTab("Dados Pessoais", createPersonalDataTab());
    }
    
}

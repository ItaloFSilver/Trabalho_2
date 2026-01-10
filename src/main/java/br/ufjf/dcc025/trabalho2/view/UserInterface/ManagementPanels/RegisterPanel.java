package br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.ufjf.dcc025.trabalho2.controller.RegisterController;
import br.ufjf.dcc025.trabalho2.controller.SecretaryController;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidRegisterException;
import br.ufjf.dcc025.trabalho2.model.users.Profile;
import br.ufjf.dcc025.trabalho2.model.users.User;


public class RegisterPanel extends JPanel{
   
    private JComboBox<String> cbTipoUsuario;
    private JLabel lblEspecializacao;
    private JTextField txtEspecializacao;
    private JFormattedTextField campoCPF;
    private JFormattedTextField campophoneNumber;
    private JTextField campoNome;
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private RegisterFrame regPage;
    private DefaultTableModel main;
    
    public RegisterPanel(RegisterFrame main, DefaultTableModel model, int index) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        this.regPage = main;
        this.main = model;

        campoNome = new JTextField(20); 
        campoEmail = new JTextField(20); 
        campoSenha = new JPasswordField(20); 
        campophoneNumber = criarCampoFormatado("(##)#####-####");
        campoCPF = criarCampoFormatado("###.###.###-##");
        
        adicionarCampo("Nome Completo:", campoNome, 0, gbc);
        adicionarCampo("Email:", campoEmail, 1, gbc);
        adicionarCampo("phoneNumber:", campophoneNumber, 2, gbc); 
        adicionarCampo("CPF:", campoCPF, 3, gbc);     
        adicionarCampo("Senha:", campoSenha, 4, gbc);

        
        JLabel lblTipo = new JLabel("Tipo de Usuário:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(lblTipo, gbc);

        String[] tipos = {"Paciente", "Secretário", "Médico"};
        cbTipoUsuario = new JComboBox<>(tipos);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(cbTipoUsuario, gbc);

        
        lblEspecializacao = new JLabel("Especialização:");
        lblEspecializacao.setVisible(false); 
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(lblEspecializacao, gbc);

        txtEspecializacao = new JTextField(20);
        txtEspecializacao.setVisible(false); // Começa invisível
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(txtEspecializacao, gbc);

        
        cbTipoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selecionado = (String) cbTipoUsuario.getSelectedItem();
                
                boolean isMedico = "Médico".equals(selecionado);
                
                //lblEspecializacao.setVisible(isMedico);
                //txtEspecializacao.setVisible(isMedico);

                //revalidate();
                //repaint();
            }
        });
        
        
        JButton btnCadastrar = new JButton("Confirmar");
        btnCadastrar.setPreferredSize(new Dimension(150, 30));
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2; // Ocupa a largura toda
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 5, 5, 5); // Mais espaço acima do botão

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    if(index >= 0)
                        removePerIndex(index, model, campoCPF.getText());
                    new RegisterController().registerUser(
                        campoNome.getText(),
                        campoEmail.getText(),
                        campoCPF.getText(),
                        campophoneNumber.getText(),
                        new String(campoSenha.getPassword()),
                        Profile.fromString((String) cbTipoUsuario.getSelectedItem())
                    );
                    String [] data = {campoNome.getText(),campoCPF.getText(),(String) cbTipoUsuario.getSelectedItem(), campoEmail.getText(),campophoneNumber.getText()};
                    model.addRow(data);
                    
                } catch (InvalidRegisterException e) {
                    JOptionPane.showMessageDialog(null, "Erro no cadastro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                regPage.setVisible(false);
            }
        });
        add(btnCadastrar, gbc);
        
        
        setBorder(BorderFactory.createTitledBorder("Novo Usuário"));
    }

    
    private void adicionarCampo(String rotulo, Component campo, int linha, GridBagConstraints gbc) {
        gbc.gridwidth = 1;
        
        JLabel label = new JLabel(rotulo);
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(campo, gbc);
    }
    
    public void setText(User user){
        campoNome.setText(user.getName());
        campoEmail.setText(user.getEmail());
        campoSenha.setText(user.getPassword());
        campophoneNumber.setText(user.getphoneNumber().toString());
        campoCPF.setText(user.getCPF().toString());
        campoCPF.setEditable(false);
    }
    
    private JFormattedTextField criarCampoFormatado(String mascara) {
        JFormattedTextField campo = null;
        try {
            MaskFormatter format = new MaskFormatter(mascara);
            format.setPlaceholderCharacter('_'); 
            campo = new JFormattedTextField(format);
            campo.setColumns(20);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return campo;
    }
    
    public void removePerIndex(int index, DefaultTableModel model, String c){
        if(index >= 0) {
            model.removeRow(index);
            SecretaryController nova = new SecretaryController();
            nova.removeUserByCPF(c);
        }
    }
}


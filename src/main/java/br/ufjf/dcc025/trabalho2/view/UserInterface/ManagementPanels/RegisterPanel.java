package br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.MaskFormatter;


public class RegisterPanel extends JPanel{
   
    private JComboBox<String> cbTipoUsuario;
    private JLabel lblEspecializacao;
    private JTextField txtEspecializacao;
    private JFormattedTextField campoCPF;
    private JFormattedTextField campoTelefone;
    
    public RegisterPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 

        campoTelefone = criarCampoFormatado("(##)#####-####");
        campoCPF = criarCampoFormatado("###.###.###-##");
        
        adicionarCampo("Nome Completo:", new JTextField(20), 0, gbc);
        adicionarCampo("Email:", new JTextField(20), 1, gbc);
        adicionarCampo("Telefone:", campoTelefone, 2, gbc); 
        adicionarCampo("CPF:", campoCPF, 3, gbc);     
        adicionarCampo("Senha:", new JPasswordField(20), 4, gbc);

        
        JLabel lblTipo = new JLabel("Tipo de Usuário:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(lblTipo, gbc);

        String[] tipos = {"Paciente", "Recepcionista", "Médico"};
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
                
                // Se for "Médico", mostra os campos. Senão, esconde.
                boolean isMedico = "Médico".equals(selecionado);
                
                lblEspecializacao.setVisible(isMedico);
                txtEspecializacao.setVisible(isMedico);

                // Revalida o layout para ajustar os espaços
                revalidate();
                repaint();
            }
        });

        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2; // Ocupa a largura toda
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 5, 5, 5); // Mais espaço acima do botão
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
}

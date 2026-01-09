package br.ufjf.dcc025.trabalho2.view.UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import br.ufjf.dcc025.trabalho2.controller.SecretaryController;
import br.ufjf.dcc025.trabalho2.model.users.User;
import br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels.RegisterFrame;
import br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels.RegisterPanel;

public class SecretaryPanel extends JPanel{
    
    private JButton logOutBtn;
    private MainFrame mainPage;
    private SecretaryController controller;
    private DefaultTableModel model;
    
    public SecretaryPanel(MainFrame main){
        this.mainPage = main;
        setLayout(new BorderLayout());

        
        JPanel pnlHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlHeader.setBackground(new Color(60, 100, 180)); // Azul profissional
        JLabel lblBemVindo = new JLabel("Olá, Secretária. Bem-vinda ao sistema.");
        lblBemVindo.setForeground(Color.WHITE);
        lblBemVindo.setFont(new Font("Arial", Font.BOLD, 14));
        pnlHeader.add(lblBemVindo);
        
        logOutBtn = new JButton("LogOut");
        logOutBtn.addActionListener(this::logOutBtnActionPerformed);
        pnlHeader.add(logOutBtn);
        
        add(pnlHeader, BorderLayout.NORTH);

        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        
        tabbedPane.addTab("Agendamentos", criarTabAgenda());
        
        
        tabbedPane.addTab("Gerenciar Usuários", criarTabUsuarios());

        add(tabbedPane, BorderLayout.CENTER);
    }

    
    private JPanel criarTabAgenda() {
        JPanel painel = new JPanel(new BorderLayout());

        
        String[] colunas = {"Data", "Hora", "Paciente", "Médico", "Status"};
        Object[][] dados = {
            {"08/01/2026", "09:00", "João Silva", "Dr. House", "Confirmado"},
            {"08/01/2026", "10:30", "Maria Oliveira", "Dra. Grey", "Pendente"},
            {"08/01/2026", "14:00", "Carlos Souza", "Dr. House", "Cancelado"}
        };

        DefaultTableModel model = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(model);
        
        
        JScrollPane scroll = new JScrollPane(tabela);
        painel.add(scroll, BorderLayout.CENTER);

        
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.add(new JButton("Novo Agendamento"));
        toolbar.add(new JButton("Filtrar por Médico"));
        painel.add(toolbar, BorderLayout.NORTH);

        return painel;
    }

    
    private JPanel criarTabUsuarios() {
        JPanel painel = new JPanel(new BorderLayout());

        String[] colunas = { "Nome", "Documento", "Tipo", "Email", "phoneNumber"};

        model = new DefaultTableModel(colunas,0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tabela = new JTable(model);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scroll = new JScrollPane(tabela);
        painel.add(scroll, BorderLayout.CENTER);
        
        controller = new SecretaryController();
        
        List<User> listaUsuarios = controller.listAllUsers();
        
        for(User u : listaUsuarios){
            String [] data = {u.getName(), u.getCPF().toString(), " ", u.getEmail(), u.getphoneNumber().toString()};
            model.addRow(data);
        }
        
        JPanel pnlBotoes = new JPanel();
        JButton btnNovo = new JButton("Cadastrar Novo Usuário");
        JButton btnEditar = new JButton("Editar Selecionado");
        JButton btnExcluir = new JButton("Excluir");

        
        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirJanelaCadastro();
                
            }
        });
        btnEditar.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                int linha = tabela.getSelectedRow();
                if (linha != -1) {
                    String nome = tabela.getValueAt(linha, 0).toString();
                    JOptionPane.showMessageDialog(null, "Editar: " + nome);
                    
                    int line = tabela.getSelectedRow();
                    int modelRow = tabela.convertRowIndexToModel(line);
                    String cpf = tabela.getModel().getValueAt(modelRow, 1).toString();
                    
                    User user = listaUsuarios.get(tabela.getSelectedRow());
                    
                    model.removeRow(tabela.getSelectedRow());
                    controller.removeUserByCPF(cpf);
                    
                    
                    RegisterFrame edit = new RegisterFrame(model);
                    RegisterPanel editar = new RegisterPanel(edit, model);
                    edit.add(editar);
                    editar.setText(user);
                    edit.setContentPane(editar);
                    edit.pack();
                    edit.setLocationRelativeTo(null);
                    edit.setSize(640, 480);
                    edit.setVisible(true);
                }
            }
        });
        
        btnExcluir.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent evt) {
                int line = tabela.getSelectedRow();
                int modelRow = tabela.convertRowIndexToModel(line);
                String cpf = tabela.getModel().getValueAt(modelRow, 1).toString();
                controller.removeUserByCPF(cpf);
                model.removeRow(tabela.getSelectedRow());     
            }
            });
        
        pnlBotoes.add(btnNovo);
        pnlBotoes.add(btnEditar);
        pnlBotoes.add(btnExcluir);

        painel.add(pnlBotoes, BorderLayout.SOUTH);

        return painel;
    }

    
    private void abrirJanelaCadastro() {

        RegisterFrame dialog = new RegisterFrame(model);
        dialog.pack(); 
        dialog.setLocationRelativeTo(null); 
        dialog.setSize(640, 480);
        dialog.setVisible(true);
    }
    
    private void logOutBtnActionPerformed(ActionEvent evt){
        this.mainPage.changeScreen("login");
    }
}


package br.ufjf.dcc025.trabalho2.view.UserInterface;

import br.ufjf.dcc025.trabalho2.controller.AppointmentController;
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
import br.ufjf.dcc025.trabalho2.model.services.Appointment;
import br.ufjf.dcc025.trabalho2.model.users.User;
import br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels.AppointPanel;
import br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels.RegisterFrame;
import br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels.RegisterPanel;
import javax.swing.JFrame;

public class SecretaryPanel<Secretary> extends UserPanel{
    
    private JButton logOutBtn;
    private MainFrame mainPage;
    private SecretaryController controller;
    private DefaultTableModel model;
    
    public SecretaryPanel(MainFrame main){
        super(main);
        tabbedPane.addTab("Agendamentos", criarTabAgenda());
        tabbedPane.addTab("Gerenciar Usuários", criarTabUsuarios());

        add(tabbedPane, BorderLayout.CENTER);
    }

    
    private JPanel criarTabAgenda() {
        
        JPanel painel = new JPanel(new BorderLayout());
        JButton appointAddBtn = new JButton("Novo Agendamento");
        JButton deleteBtn = new JButton("Desmarcar Consulta");
        
        AppointmentController appController = new AppointmentController();
        
        List<Appointment> agenda = appController.listAll();
        
        String[] colunas = {"Data", "Paciente", "Médico", "Status"};
        
        DefaultTableModel appoint = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(appoint);
        
        for(Appointment a : agenda){
            String [] data = {a.getDate(), a.getPatientName(), a.getMedicName(), a.getCheck()};
            appoint.addRow(data);
        }
        
        JScrollPane scroll = new JScrollPane(tabela);
        painel.add(scroll, BorderLayout.CENTER);
        
        
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.add(appointAddBtn);
        toolbar.add(deleteBtn);
        painel.add(toolbar, BorderLayout.NORTH);

        appointAddBtn.addActionListener((ActionEvent e) -> {
            JFrame appointmentFrame = new JFrame();
            appointmentFrame.add(new AppointPanel(agenda, appoint,appointmentFrame));
            
            appointmentFrame.pack();
            appointmentFrame.setLocationRelativeTo(null);
            appointmentFrame.setVisible(true);
        });
        
        deleteBtn.addActionListener((ActionEvent e) -> {
            if(tabela.getSelectedRow()>=0){
                int index = tabela.getSelectedRow();
                Appointment a = agenda.get(index);
                agenda.remove(index);
                
                System.out.println(tabela.getSelectedRow());
                appController.removeAppointment(a);
                appoint.removeRow(tabela.getSelectedRow());
            }
        });
        
        
        return painel;
    }

    
    private JPanel criarTabUsuarios() {
        JPanel painel = new JPanel(new BorderLayout());

        String[] colunas = {"Nome", "Documento", "Tipo", "Email", "phoneNumber"};

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
            String [] data = {u.getName(), u.getCPF().toString(), u.getProfile().toString(), u.getEmail(), u.getphoneNumber().toString()};
            model.addRow(data);
        }
        
        JPanel pnlBotoes = new JPanel();
        JButton btnNovo = new JButton("Cadastrar Novo Usuário");
        JButton btnEditar = new JButton("Editar Selecionado");
        JButton btnExcluir = new JButton("Excluir");

        
        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegisterWindow();
                
            }
        });
        //Se a ação é confirmada, o usuário passado é deletado e registra-se um novo em cima do anterior. CPF não editável
        btnEditar.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                int linha = tabela.getSelectedRow();
                if (linha != -1) {
                    String nome = tabela.getValueAt(linha, 0).toString();
                    JOptionPane.showMessageDialog(null, "Editar: " + nome);
                    
                    User user = listaUsuarios.get(tabela.getSelectedRow());

                    RegisterFrame edit = new RegisterFrame(model, tabela.getSelectedRow());
                    RegisterPanel editar = new RegisterPanel(edit, model, tabela.getSelectedRow());
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
                deleteAppointments(cpf);
            }
            });
        
        pnlBotoes.add(btnNovo);
        pnlBotoes.add(btnEditar);
        pnlBotoes.add(btnExcluir);

        painel.add(pnlBotoes, BorderLayout.SOUTH);

        return painel;
    }

    
    private void openRegisterWindow() {
        RegisterFrame dialog = new RegisterFrame(model, -1);
        dialog.pack(); 
        dialog.setLocationRelativeTo(null); 
        dialog.setSize(640, 480);
        dialog.setVisible(true);
    }
    
    private void deleteAppointments(String cpf){
        AppointmentController controller = new AppointmentController();
        controller.removeAllOfUser(cpf);
        
    }
}


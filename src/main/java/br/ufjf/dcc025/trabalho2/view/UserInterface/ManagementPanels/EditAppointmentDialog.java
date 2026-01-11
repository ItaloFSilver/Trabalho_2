package br.ufjf.dcc025.trabalho2.view.UserInterface.ManagementPanels;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class EditAppointmentDialog extends JDialog {

    private JComboBox<String> comboHorarios;
    private boolean salvou = false;
    private boolean desmarcou = false;
    private String novoHorarioSelecionado;

    /**
     * @param parent A janela principal
     * @param medicoNome O nome do médico (apenas para exibição)
     * @param horarioAtual O horário que o paciente já tem agendado (ex: "10/01/2026 14:00")
     * @param horariosDisponiveis Lista de horários livres vindos do banco de dados/JSON
     */
    public EditAppointmentDialog(Frame parent, String medicoNome, String horarioAtual, List<String> horariosDisponiveis) {
        super(parent, "Editar Consulta", true);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // --- PAINEL CENTRAL ---
        JPanel panelCenter = new JPanel(new GridLayout(4, 1, 5, 5));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblMedico = new JLabel("Médico: " + medicoNome);
        lblMedico.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblInstrucao = new JLabel("Selecione um novo horário disponível:");

        
        comboHorarios = new JComboBox<>();
        
        // 1. Adiciona o horário ATUAL primeiro (para ele não sumir da lista)
        comboHorarios.addItem(horarioAtual);
        
        // 2. Adiciona os horários DISPONÍVEIS
        for (String horario : horariosDisponiveis) {
            // Evita duplicar se o horário atual já estiver na lista de disponíveis
            if (!horario.equals(horarioAtual)) {
                comboHorarios.addItem(horarioAtual+horario);
            }
        }
        
        // Seleciona o horário atual por padrão
        comboHorarios.setSelectedItem(horarioAtual);

        panelCenter.add(lblMedico);
        panelCenter.add(lblInstrucao);
        panelCenter.add(comboHorarios);

        add(panelCenter, BorderLayout.CENTER);

        
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton btnDesmarcar = new JButton("Desmarcar");
        btnDesmarcar.setBackground(new Color(255, 102, 102));
        btnDesmarcar.setForeground(Color.WHITE);

        JButton btnCancelar = new JButton("Cancelar");
        JButton btnConfirmar = new JButton("Confirmar");

        
        btnDesmarcar.addActionListener(e -> {
            int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar esta consulta?", "Atenção", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                desmarcou = true;
                dispose();
            }
        });

        btnConfirmar.addActionListener(e -> {
            // Pega o item selecionado no ComboBox
            novoHorarioSelecionado = comboHorarios.getSelectedItem().toString();
            
            // Verifica se mudou algo
            if (novoHorarioSelecionado.equals(horarioAtual)) {
                JOptionPane.showMessageDialog(this, "Você manteve o mesmo horário.");
                // Não marcamos 'salvou' como true se não houve mudança real, ou marcamos, depende da sua regra.
                // Aqui vou fechar sem ação.
                dispose(); 
            } else {
                salvou = true;
                dispose();
            }
        });

        btnCancelar.addActionListener(e -> dispose());

        panelButtons.add(btnDesmarcar);
        panelButtons.add(btnCancelar);
        panelButtons.add(btnConfirmar);
        add(panelButtons, BorderLayout.SOUTH);
    }

    public boolean isSalvou() { return salvou; }
    public boolean isDesmarcou() { return desmarcou; }
    public String getNovoHorario() { return novoHorarioSelecionado; }
}
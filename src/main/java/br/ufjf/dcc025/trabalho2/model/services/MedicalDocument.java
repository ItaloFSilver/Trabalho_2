package br.ufjf.dcc025.trabalho2.model.services;

import java.util.Date;

public class MedicalDocument {
    private String tipo; // "Atestado" ou "Exame"
    private String doctorCpf;
    private String patientCpf;
    private String diagnostico;
    private String recomendacao; // Receita ou instruções do exame
    private Date dataEmissao;

    public MedicalDocument(String tipo, String doctorCpf, String patientCpf, String diagnostico, String recomendacao) {
        this.tipo = tipo;
        this.doctorCpf = doctorCpf;
        this.patientCpf = patientCpf;
        this.diagnostico = diagnostico;
        this.recomendacao = recomendacao;
        this.dataEmissao = new Date(); 
    }

    public String getTipo() { return tipo; }
    public String getDoctorCpf() { return doctorCpf; }
    public String getPatientCpf() { return patientCpf; }
    public String getDiagnostico() { return diagnostico; }
    public String getRecomendacao() { return recomendacao; }
    public Date getDataEmissao() { return dataEmissao; }
}

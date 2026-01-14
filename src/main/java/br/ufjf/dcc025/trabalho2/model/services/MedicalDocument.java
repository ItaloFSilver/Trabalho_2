/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.model.services;

import java.util.Date;

public class MedicalDocument {
    private final String tipo; // "Atestado" ou "Exame"
    private final String doctorCpf;
    private final String patientCpf;
    private final String diagnostico;
    private final String recomendacao; // Receita ou instruções do exame
    private final Date dataEmissao;

    public MedicalDocument(String tipo, String doctorCpf, String patientCpf, String diagnostico, String recomendacao) {
        this.tipo = tipo;
        this.doctorCpf = doctorCpf;
        this.patientCpf = patientCpf;
        this.diagnostico = diagnostico;
        this.recomendacao = recomendacao;
        this.dataEmissao = new Date(); 
    }

    //retorna se é um atestado, exame ou receita
    public String getTipo() { return tipo; }
    
    //retorna o cpf do médico
    public String getDoctorCpf() { return doctorCpf; }
    
    //retorna o cpf do paciente
    public String getPatientCpf() { return patientCpf; }
    
    //retorna o diagnóstico
    public String getDiagnostico() { return diagnostico; }
    
    //retorna a recomendação do médico
    public String getRecomendacao() { return recomendacao; }
    
    //retorna a data em que foi emitido
    public Date getDataEmissao() { return dataEmissao; }
}

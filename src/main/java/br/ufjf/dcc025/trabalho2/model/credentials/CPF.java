/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
package br.ufjf.dcc025.trabalho2.model.credentials;

import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidCPFException;

public class CPF {

    private final String cpf;
    private final static transient String regex = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$";

    public CPF(String cpf) throws InvalidCPFException{
        if(!validateCPF(this.normalize(cpf))) {
            throw new InvalidCPFException("CPF invalido");
        }
        else {
            this.cpf = cpf;
        }
    }

    //método que valida maticamente o CPF seguindo as normas atuais do governo
    private boolean validateCPFMathematicaly(String cpf) {
        int firstSum = 0;
        int index = 0;

        for(int i = 10; i >= 2; i--) {
            char c = cpf.charAt(index);
            firstSum += Character.getNumericValue(c) * i;
            index++;
        }

        int secondSum = 0;
        index = 0;
        for(int i = 11; i >= 2; i--) {
            char c = cpf.charAt(index);
            secondSum += Character.getNumericValue(c) * i;
            index++;
        }

        return (firstSum * 10) % 11 == Character.getNumericValue(cpf.charAt(9)) &&
               (secondSum * 10) % 11 == Character.getNumericValue(cpf.charAt(10));
    }

    //valida se o cpf informado segue a forma ###.###.###-##
    private boolean validateCPF(String cpf) {
        if(cpf.matches(regex)) {
            return validateCPFMathematicaly(cpf);
        }
        return false;
    }    

    //retorna o CPF como uma string
    private String getCPF() {
        return cpf;
    }

    // Remove tudo que não for número
    public String normalize(String cpf) {
        return cpf.replaceAll("[^0-9]", ""); 
    }

    @Override
    public String toString() {
        /*return cpf.charAt(0) + cpf.charAt(1) + cpf.charAt(2) + "." + cpf.charAt(3) + cpf.charAt(4)
               + cpf.charAt(5) + "." + cpf.charAt(6) + cpf.charAt(7) + cpf.charAt(8) + "-" 
               + cpf.charAt(9) + cpf.charAt(10);
    */
        return cpf;
    }

    @Override
    public int hashCode() {
        return cpf.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        CPF other = (CPF) obj;
        return cpf.equals(other.getCPF());
    }
}
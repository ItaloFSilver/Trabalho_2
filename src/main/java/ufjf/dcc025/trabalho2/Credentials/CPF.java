package ufjf.dcc025.trabalho2.Credentials;

import ufjf.dcc025.trabalho2.Error.InvalidCPFException;

public class CPF {

    private final String cpf;

    private String regex = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$";

    boolean validateCPFMathematicaly(String cpf) {
        int firstSum = 0;
        int index = 0;

        for(int i = 10; i >= 2; i--) {
            char c = cpf.charAt(index);
            firstSum += Character.getNumericValue(c) * i;
        }

        int secondSum = 0;
        index = 0;
        for(int i = 11; i >= 2; i--) {
            char c = cpf.charAt(index);
            secondSum += Character.getNumericValue(c) * i;
        }

        return (firstSum * 10) % 11 == Character.getNumericValue(cpf.charAt(9)) &&
                (secondSum * 10) % 11 == Character.getNumericValue(cpf.charAt(10));
    }

    boolean validateCPF(String cpf) throws InvalidCPFException {
        if(!cpf.matches(regex)) {
            return validateCPFMathematicaly(cpf);
        }
        return false;
    }

    private CPF(String cpf) throws InvalidCPFException{
        if(!validateCPF(cpf)) {
            throw new InvalidCPFException("CPF invalido");
        }
        else {
            this.cpf = cpf;
        }
    }

    @Override
    public String toString() {
        return cpf.charAt(0) + cpf.charAt(1) + cpf.charAt(2) + "." + cpf.charAt(3) + cpf.charAt(4)
                + cpf.charAt(5) + "." + cpf.charAt(6) + cpf.charAt(7) + cpf.charAt(8) + "-" 
                + cpf.charAt(9) + cpf.charAt(10);
    }

}
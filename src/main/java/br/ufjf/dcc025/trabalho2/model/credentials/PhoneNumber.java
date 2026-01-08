package br.ufjf.dcc025.trabalho2.model.credentials;

import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidphoneNumberException;

public class PhoneNumber {

    private final String phoneNumber;

    private static transient final String regex = "^(\\(?\\d{2}\\)?\\s?)?9\\d{4}-?\\d{4}$";

    public PhoneNumber(String phoneNumber) throws InvalidphoneNumberException {
        phoneNumber = this.normalize(phoneNumber);

        if(!validatephoneNumber(phoneNumber)) {
            throw new InvalidphoneNumberException("phoneNumber invalido");
        }
        this.phoneNumber = phoneNumber;
    }

    private boolean validatephoneNumber(String phoneNumber) {
        return phoneNumber.matches(regex);
    }

    private String normalize(String phoneNumber) {
        return phoneNumber.replaceAll("[^0-9]", ""); // Remove tudo que não for número
    }

    @Override
    public String toString() {
        return "(" + phoneNumber.charAt(0) + phoneNumber.charAt(1) + ")" + phoneNumber.charAt(2) + phoneNumber.charAt(3) + 
                phoneNumber.charAt(4) + phoneNumber.charAt(5) + phoneNumber.charAt(6) + "-" + phoneNumber.charAt(7) + 
                phoneNumber.charAt(8) + phoneNumber.charAt(9) + phoneNumber.charAt(10);
    }
}
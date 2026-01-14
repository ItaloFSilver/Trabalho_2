/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/

package br.ufjf.dcc025.trabalho2.model.credentials;

import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidphoneNumberException;

public class PhoneNumber {

    private final String phoneNumber;

    private static transient final String regex = "^(\\(?\\d{2}\\)?\\s?)?9\\d{4}-?\\d{4}$";

    //constutor
    public PhoneNumber(String phoneNumber) throws InvalidphoneNumberException {
        if(!validatephoneNumber(this.normalize(phoneNumber))) {
            throw new InvalidphoneNumberException("phoneNumber invalido");
        }
        this.phoneNumber = phoneNumber;
    }

    //valida se o número informado está no formato (##)#####-####
    private boolean validatephoneNumber(String phoneNumber) {
        return phoneNumber.matches(regex);
    }

    //remove tudo o que não for dígito no número de telefone
    private String normalize(String phoneNumber) {
        return phoneNumber.replaceAll("[^0-9]", "");
    }

    //retorna o número de telefone como uma String
    @Override
    public String toString() {
        return this.phoneNumber;
    }
}
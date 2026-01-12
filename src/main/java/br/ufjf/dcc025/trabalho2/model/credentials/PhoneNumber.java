/*
*Arthur de Souza Marques - 202435015
*Ítalo Fagundes Silvério - 202435020
*/
/*Arthur de Souza Marques - 202435015 */
package br.ufjf.dcc025.trabalho2.model.credentials;

import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidphoneNumberException;

public class PhoneNumber {

    private final String phoneNumber;

    private static transient final String regex = "^(\\(?\\d{2}\\)?\\s?)?9\\d{4}-?\\d{4}$";

    public PhoneNumber(String phoneNumber) throws InvalidphoneNumberException {
        if(!validatephoneNumber(this.normalize(phoneNumber))) {
            throw new InvalidphoneNumberException("phoneNumber invalido");
        }
        this.phoneNumber = phoneNumber;
    }

    private boolean validatephoneNumber(String phoneNumber) {
        return phoneNumber.matches(regex);
    }

    private String normalize(String phoneNumber) {
        return phoneNumber.replaceAll("[^0-9]", "");
    }

    @Override
    public String toString() {
        return this.phoneNumber;
    }
}
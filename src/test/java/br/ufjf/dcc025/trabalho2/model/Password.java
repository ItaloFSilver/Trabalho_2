package br.ufjf.dcc025.trabalho2.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.ufjf.dcc025.trabalho2.model.credentials.PhoneNumber;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidphoneNumberException;

public class Password {
    
    @Test
    @DisplayName("Deve criar um phoneNumber válido sem lançar exceções")
    void shouldNotThrowWhenCreatingValidphoneNumber() {
        String validPhone = "32999239026";
        assertDoesNotThrow(() -> new PhoneNumber(validPhone)); 
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar um CPF inválido")
    void shouldThrowWhenCreatingInvalidphoneNumber() {
        String invalidPhone = "12AphoneNumber0";
        InvalidphoneNumberException e = assertThrows(InvalidphoneNumberException.class, () -> {new PhoneNumber(invalidPhone);});
        assertEquals("phoneNumber invalido", e.getMessage());
    }
}

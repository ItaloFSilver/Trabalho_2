package br.ufjf.dcc025.trabalho2.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.ufjf.dcc025.trabalho2.model.credentials.Email;
import br.ufjf.dcc025.trabalho2.model.error.InvalidEmailException;

public class EmailTest {
    
    @Test
    @DisplayName("Deve criar um email válido sem lançar exceções")
    void shouldNotThrowWhenCreatingValidEmail() {
        String validEmail = "meuemail@ufjf.br";
        assertDoesNotThrow(() -> new Email(validEmail));
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar um email inválido")
    void shouldThrowWhenCreatingInvalidEmail() {
        String invalidEmail = "emailinvalido.com";
        InvalidEmailException e = assertThrows(InvalidEmailException.class, () -> {new Email(invalidEmail);});
        assertEquals("Email invalido", e.getMessage());
    }

    @Test
    @DisplayName("Deve considerar dois emails iguais como iguais")
    void shouldTestEquals() {
        String email1 = "meuemail@ufjf.br";
        String email2 = "meuemail@ufjf.br";
        String email3 = "meuemail@ufjf.com";
        Email test1 = new Email(email1);
        Email test2 = new Email(email2);
        Email test3 = new Email(email3);
        assertEquals(test1, test2);
        assertNotEquals(test1, test3);
    }
}

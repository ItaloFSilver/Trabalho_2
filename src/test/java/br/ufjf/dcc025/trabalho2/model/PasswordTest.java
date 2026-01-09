package br.ufjf.dcc025.trabalho2.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.ufjf.dcc025.trabalho2.model.credentials.Password;
import br.ufjf.dcc025.trabalho2.model.exceptions.InvalidPasswordException;

public class PasswordTest {
    
    @Test
    @DisplayName("Deve criar uma senha válida sem lançar exceções")
    void shouldNotThrowWhenCreatingValidPassword() {
        String validPassword = "123456789";
        assertDoesNotThrow(() -> new Password(validPassword)); 
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar uma senha inválida")
    void shouldThrowWhenCreatingInvalidPassword() {
        String invalidPassword = "1234";
        InvalidPasswordException e = assertThrows(InvalidPasswordException.class, () -> {new Password(invalidPassword);});
        assertEquals("Senha precisa ter no minimo 8 caracteres", e.getMessage());
    }
}

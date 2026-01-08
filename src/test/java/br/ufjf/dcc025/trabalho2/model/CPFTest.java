package br.ufjf.dcc025.trabalho2.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.ufjf.dcc025.trabalho2.model.credentials.CPF;
import br.ufjf.dcc025.trabalho2.model.error.InvalidCPFException;

public class CPFTest {
    
    @Test
    @DisplayName("Deve criar um CPF válido sem lançar exceções")
    void shouldNotThrowWhenCreatingValidCPF() {
        String validCPF = "168.698.416-24";
        assertDoesNotThrow(() -> new CPF(validCPF));
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar um CPF inválido")
    void shouldThrowWhenCreatingInvalidCPF() {
        String invalidCPF = "12345678901";
        InvalidCPFException e = assertThrows(InvalidCPFException.class, () -> {new CPF(invalidCPF);});
        assertEquals("CPF invalido", e.getMessage());
    }

    @Test
    @DisplayName("Deve considerar dois CPFs iguais como iguais")
    void shouldTestEquals() {
        String cpf1 = "02355209693";
        String cpf2 = "02355209693";
        String cpf3 = "11111111111";
        CPF test1 = new CPF(cpf1);
        CPF test2 = new CPF(cpf2);
        CPF test3 = new CPF(cpf3);
        assertEquals(test1, test2);
        assertNotEquals(test1, test3);
    }
}

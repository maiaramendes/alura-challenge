package br.alura.utils;

import br.alura.exception.RequiredFieldException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidateBlankTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void validateIfIdIsBlank_throws(final String param) {
        //then
        final var exception = assertThrows(RequiredFieldException.class,
                () -> ValidateBlank.validateIfIdIsBlank(param));

        //then
        assertEquals("Field id is required", exception.getMessage());
    }
}

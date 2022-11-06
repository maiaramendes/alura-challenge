package br.alura.utils;

import br.alura.exception.RequiredFieldException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

@Slf4j
public class ValidateBlank {

    public static void validateIfIdIsBlank(final String id) {
        log.info("Validating if id field is not filled.");

        if (Strings.isBlank(id)) {
            log.error("Field id is required.");
            throw new RequiredFieldException("Field id is required");
        }
    }
}

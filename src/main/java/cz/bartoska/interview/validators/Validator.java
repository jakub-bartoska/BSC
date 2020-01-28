package cz.bartoska.interview.validators;

import javax.validation.ValidationException;

/**
 * Validator.
 */
public interface Validator {

    /**
     * Validates input.
     */
    void validate(String input) throws ValidationException;
}

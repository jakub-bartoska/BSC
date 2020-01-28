package cz.bartoska.interview.validators;

import javax.validation.ValidationException;

/**
 * Empty validator.
 */
public class EmptyValidator implements Validator {

    @Override
    public void validate(String input) throws ValidationException {

    }
}

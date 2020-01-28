package cz.bartoska.interview.validators;

import javax.validation.ValidationException;

/**
 * Validator for Quit command.
 */
public class QuitCommandValidator implements Validator {

    @Override
    public void validate(String input) throws ValidationException {
        if(!input.matches("^quit$")){
            throw new ValidationException("Input is not in correct format");
        }
    }
}

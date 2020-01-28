package cz.bartoska.interview.validators;

import javax.validation.ValidationException;

/**
 * Validator for Help command.
 */
public class HelpCommandValidation implements Validator {

    @Override
    public void validate(String input) throws ValidationException {
        if(!input.matches("^help$")){
            throw new ValidationException("Input is not in correct format");
        }
    }
}

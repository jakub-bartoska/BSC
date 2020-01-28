package cz.bartoska.interview.validators;

import cz.bartoska.interview.utils.InputParserutils;

import javax.validation.ValidationException;
import java.io.File;

/**
 * Validator for fee from file command.
 */
public class FeesFromFileCommandValidator implements Validator {

    @Override
    public void validate(String input) throws ValidationException {
        if(!input.matches("^fee-file\\s.*$")){
            throw new ValidationException("Input is not in correct format");
        }
        File file = InputParserutils.inputToFile(input);
        if(!file.exists()){
            throw new ValidationException("File does not exist.");
        }
    }
}

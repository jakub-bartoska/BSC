package cz.bartoska.interview.validators;

import cz.bartoska.interview.model.WeightPostCodePair;
import cz.bartoska.interview.utils.InputParserutils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ValidationException;
import java.math.BigDecimal;

/**
 * Validator for Add command.
 */
public class AddCommandValidator implements Validator {

    Logger logger = LoggerFactory.getLogger(AddCommandValidator.class);

    @Override
    public void validate(String input) throws ValidationException {
        if(!input.matches("^\\d{1,}(\\.\\d{1,3})?\\s{1}\\d{5}$")){
            throw new ValidationException("Input is not in correct format");
        }
        WeightPostCodePair weightPostCodePair = InputParserutils.inputToWeightPostPair(input);

        if (weightPostCodePair.getWeight().compareTo(BigDecimal.ZERO) <= 0){
            throw new ValidationException("weight is not larger than zero");
        }
    }
}

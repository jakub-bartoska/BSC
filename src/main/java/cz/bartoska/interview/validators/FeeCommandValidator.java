package cz.bartoska.interview.validators;

import cz.bartoska.interview.model.WeightFeePair;
import cz.bartoska.interview.utils.InputParserutils;

import javax.validation.ValidationException;
import java.math.BigDecimal;

/**
 * Validator for Fee command.
 */
public class FeeCommandValidator implements Validator {

    @Override
    public void validate(String input) throws ValidationException {
        if(!input.matches("^\\d{1,}(\\.\\d{1,3})?\\s{1}\\d{1,}(\\.\\d{1,2})?$")){
            throw new ValidationException("Input is not in correct format");
        }
        WeightFeePair weightFeePair = InputParserutils.inputToWeightFeePair(input);

        if (weightFeePair.getWeight().compareTo(BigDecimal.ZERO) <= 0){
            throw new ValidationException("Weight is not larger than 0");
        }

        if (weightFeePair.getFee().compareTo(BigDecimal.ZERO) <= 0){
            throw new ValidationException("Fee is not larger than 0");
        }
    }
}

package cz.bartoska.interview.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ValidationException;

@RunWith(SpringRunner.class)
public class AddCommandValidatorTest {

    private final AddCommandValidator addCommandValidator = new AddCommandValidator();

    @Test
    public void positiveValidation(){
        addCommandValidator.validate("12.2 12345");
    }

    @Test
    public void positiveValidationWithoutDecimalPoint(){
        addCommandValidator.validate("122 12345");
    }

    @Test(expected = ValidationException.class)
    public void validationErrorShortPostCode(){
        addCommandValidator.validate("12.2 1234");
    }

    @Test(expected = ValidationException.class)
    public void validationErrorLongPostCode(){
        addCommandValidator.validate("12.2 123456");
    }

    @Test(expected = ValidationException.class)
    public void validationErrorTooManyDecimalPlaces(){
        addCommandValidator.validate("12.2000 1234");
    }

    @Test(expected = ValidationException.class)
    public void validationErrorIncorrectSeparator(){
        addCommandValidator.validate("12,2 1234");
    }

    @Test(expected = ValidationException.class)
    public void validationErrorTwoSpaces(){
        addCommandValidator.validate("12.2  1234");
    }

    @Test(expected = ValidationException.class)
    public void validationErrorZeroWeight(){
        addCommandValidator.validate("0 1234");
    }
}

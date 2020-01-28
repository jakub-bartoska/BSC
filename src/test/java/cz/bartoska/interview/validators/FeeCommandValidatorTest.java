package cz.bartoska.interview.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ValidationException;

@RunWith(SpringRunner.class)
public class FeeCommandValidatorTest {

    private final FeeCommandValidator feeCommandValidator = new FeeCommandValidator();

    @Test
    public void positiveValidation(){
        feeCommandValidator.validate("12.2 12345");
    }

    @Test
    public void positiveValidationWithoutDecimalPoint(){
        feeCommandValidator.validate("122 12345");
    }

    @Test(expected = ValidationException.class)
    public void validationErrorTooManyDecimalPlaces(){
        feeCommandValidator.validate("12.2000 1234");
    }

    @Test(expected = ValidationException.class)
    public void validationErrorTooManyDecimalPlacesFee(){
        feeCommandValidator.validate("12.200 1234.121");
    }

    @Test(expected = ValidationException.class)
    public void validationErrorIncorrectSeparator(){
        feeCommandValidator.validate("12,2 1234");
    }

    @Test(expected = ValidationException.class)
    public void validationErrorTwoSpaces(){
        feeCommandValidator.validate("12.2  1234");
    }

    @Test(expected = ValidationException.class)
    public void validationErrorZeroWeight(){
        feeCommandValidator.validate("0 1234");
    }

    @Test(expected = ValidationException.class)
    public void validationErrorZeroFee(){
        feeCommandValidator.validate("1 0");
    }
}

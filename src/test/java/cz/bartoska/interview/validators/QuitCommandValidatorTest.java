package cz.bartoska.interview.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ValidationException;

@RunWith(SpringRunner.class)
public class QuitCommandValidatorTest {

    private final QuitCommandValidator quitCommandValidator = new QuitCommandValidator();

    @Test
    public void positiveValidation(){
        quitCommandValidator.validate("quit");
    }

    @Test(expected = ValidationException.class)
    public void validationError(){
        quitCommandValidator.validate("quit random text");
    }
}

package cz.bartoska.interview.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ValidationException;

@RunWith(SpringRunner.class)
public class HelpCommandValidationTest {

    private HelpCommandValidation helpCommandValidation = new HelpCommandValidation();

    @Test
    public void positiveValidation(){
        helpCommandValidation.validate("help");
    }

    @Test(expected = ValidationException.class)
    public void validationErrorWhiteSpace(){
        helpCommandValidation.validate("help ");
    }

    @Test(expected = ValidationException.class)
    public void validationErrorDifferentCommand(){
        helpCommandValidation.validate("add");
    }
}

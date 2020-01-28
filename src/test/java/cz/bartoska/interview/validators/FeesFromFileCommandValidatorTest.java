package cz.bartoska.interview.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ValidationException;

@RunWith(SpringRunner.class)
public class FeesFromFileCommandValidatorTest {

    private FeesFromFileCommandValidator feesFromFileCommandValidator = new FeesFromFileCommandValidator();

    @Test
    public void validInput() {
        feesFromFileCommandValidator.validate("fee-file src/test/resources/commands/fee");
    }

    @Test(expected = ValidationException.class)
    public void invalidCommandName() {
        feesFromFileCommandValidator.validate("fee-fil src/test/resources/commands/fee");
    }

    @Test(expected = ValidationException.class)
    public void notExistingFile() {
        feesFromFileCommandValidator.validate("fee-file src/test/resources/commands/fake-file");
    }
}

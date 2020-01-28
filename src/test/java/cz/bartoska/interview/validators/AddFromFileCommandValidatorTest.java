package cz.bartoska.interview.validators;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ValidationException;

@RunWith(SpringRunner.class)
public class AddFromFileCommandValidatorTest {

    private AddFromFileCommandValidator addFromFileCommandValidator = new AddFromFileCommandValidator();

    @Test
    public void validInput() {
        addFromFileCommandValidator.validate("add-file src/test/resources/commands/add");
    }

    @Test(expected = ValidationException.class)
    public void invalidCommandName() {
        addFromFileCommandValidator.validate("add-fil src/test/resources/commands/add");
    }

    @Test(expected = ValidationException.class)
    public void notExistingFile() {
        addFromFileCommandValidator.validate("add-fil src/test/resources/commands/fake-file");
    }
}

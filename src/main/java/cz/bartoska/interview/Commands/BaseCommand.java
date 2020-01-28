package cz.bartoska.interview.Commands;

import cz.bartoska.interview.validators.EmptyValidator;
import cz.bartoska.interview.validators.Validator;

import javax.validation.ValidationException;

public abstract class BaseCommand implements Command {

    private final String command;
    private final String description;
    private final Validator validator;

    public BaseCommand(String command, String description) {
        this(command, description, new EmptyValidator());
    }

    public BaseCommand(String command, String description, Validator validator) {
        this.command = command;
        this.description = description;
        this.validator = validator;
    }

    @Override
    public String getCommand()  {
        return command;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute(String input) {
        throw new UnsupportedOperationException("Execute command is not implemented");
    }

    @Override
    public void validate(String input) throws ValidationException {
        validator.validate(input);
    }
}

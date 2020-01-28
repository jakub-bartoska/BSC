package cz.bartoska.interview.Commands;

import javax.validation.ValidationException;

/**
 * Commandline command.
 */
public interface Command {

    /**
     * Returns command line command.
     */
    String getCommand();

    /**
     * Returns description for this command.
     */
    String getDescription();

    /**
     * Executes this command.
     */
    void execute(String input);

    /**
     * Validates this command.
     * @param input
     * @throws ValidationException if input is not valid.
     */
    void validate(String input) throws ValidationException;
}

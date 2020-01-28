package cz.bartoska.interview.service;

import cz.bartoska.interview.Commands.AddCommand;
import cz.bartoska.interview.Commands.Command;
import cz.bartoska.interview.Commands.HelpCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

/**
 * takes care about command execution.
 */
@Service
public class CommandExecutor {

    Logger logger = LoggerFactory.getLogger(CommandExecutor.class);

    private final List<Command> commands;
    private final AddCommand defaultCommand;
    private final HelpCommand helpCommand;

    @Autowired
    public CommandExecutor(List<Command> commands, AddCommand defaultCommand) {
        this.commands = commands;
        this.defaultCommand = defaultCommand;
        helpCommand = new HelpCommand(this.commands);
        this.commands.add(helpCommand);
    }

    public void executeProperCommand(String input) {
        Command command = selectProperCommand(input);
        logger.info("Executing command {}", command.getCommand());
        try {
            command.validate(input);
            command.execute(input);
        } catch (ValidationException e) {
            logger.error("Input command ({}) was not properly formated! Execute command '{}' to get info how to use " +
                    "application", input, helpCommand.getCommand());
        }
    }

    private Command selectProperCommand(String input) {
        return commands
                .stream()
                .filter(command -> input.startsWith(command.getCommand() + " ") || input.equals(command.getCommand()))
                .findFirst()
                .orElse(defaultCommand);
    }

}

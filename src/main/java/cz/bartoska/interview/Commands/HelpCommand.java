package cz.bartoska.interview.Commands;

import cz.bartoska.interview.validators.HelpCommandValidation;

import java.util.List;

/**
 * Prints all command descriptions
 */
public class HelpCommand extends BaseCommand {

    private final List<Command> commands;

    public HelpCommand(List<Command> commands) {
        super("help", "Prints all command descriptions", new HelpCommandValidation());
        this.commands = commands;
    }

    @Override
    public void execute(String input) {
        commands.forEach(command -> {
            System.out.println(command.getCommand() + " - " + command.getDescription());
        });
    }
}

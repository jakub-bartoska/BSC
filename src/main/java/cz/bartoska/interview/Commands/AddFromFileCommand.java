package cz.bartoska.interview.Commands;

import cz.bartoska.interview.utils.InputParserutils;
import cz.bartoska.interview.validators.AddCommandValidator;
import cz.bartoska.interview.validators.AddFromFileCommandValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Loads file which contains weights and post codes. Data inside this file are used in the same way as in default command and
 * also should be in the same format.
 * Format of this command is:
 * add-file <path-to-file>
 */
@Service
public class AddFromFileCommand extends BaseCommand {

    Logger logger = LoggerFactory.getLogger(AddFromFileCommand.class);
    private final AddCommand addCommand;

    @Autowired
    public AddFromFileCommand(AddCommand addCommand) {
        super("add-file", "Loads file which contains weights and post codes. Data inside this file are used in the same way as in default command and\n" +
                " also should be in the same format.\n" +
                " Format of this command is:\n" +
                " add-file <path-to-file>", new AddFromFileCommandValidator());
        this.addCommand = addCommand;
    }

    @Override
    public void execute(String input) {
        File file = InputParserutils.inputToFile(input);
        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
            AddCommandValidator addCommandValidator = new AddCommandValidator();
            stream.forEach(line -> {
                try{
                    addCommandValidator.validate(line);
                } catch (ValidationException e){
                    logger.error("line '{}' was not properly formated, so it is being skipped", line);
                    return;
                }
                addCommand.execute(line);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

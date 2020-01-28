package cz.bartoska.interview.commands;

import cz.bartoska.interview.Commands.AddCommand;
import cz.bartoska.interview.Commands.Command;
import cz.bartoska.interview.Commands.FeesFromFileCommand;
import cz.bartoska.interview.Commands.HelpCommand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class HelpCommandTest {

    private HelpCommand helpCommand;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void init() {
        List<Command> commands = new ArrayList<>();
        commands.add(new AddCommand(null));
        commands.add(new FeesFromFileCommand(null));
        helpCommand = new HelpCommand(commands);
        commands.add(helpCommand);
    }

    @Test
    public void executeAction() throws IOException {
        System.setOut(new PrintStream(outContent));

        helpCommand.execute("");

        File file = new File("src/test/resources/commands/help-result");
        String result = Files.readString(Paths.get(file.getPath()));
        assertEquals(cleanNewLines(result), cleanNewLines(outContent.toString()));
    }

    private String cleanNewLines(String string){
        return string.replaceAll("\\n", "").replaceAll("\\r", "");
    }
}

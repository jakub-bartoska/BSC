package cz.bartoska.interview.commands;

import cz.bartoska.interview.Commands.AddCommand;
import cz.bartoska.interview.Commands.AddFromFileCommand;
import cz.bartoska.interview.model.WeightPostCodePair;
import cz.bartoska.interview.repository.PostcodeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class AddFromFileCommandTest {

    private PostcodeRepository postcodeRepository;
    private AddFromFileCommand addFromFileCommand;

    @Before
    public void init(){
        postcodeRepository = new PostcodeRepository();
        AddCommand addCommand = new AddCommand(postcodeRepository);
        addFromFileCommand = new AddFromFileCommand(addCommand);
    }

    @Test
    public void executeAction() {
        addFromFileCommand.execute("add-file src/test/resources/commands/add");

        List<WeightPostCodePair> sortedRecords = postcodeRepository.getSortedRecords();
        assertEquals(7, sortedRecords.size());
    }
}

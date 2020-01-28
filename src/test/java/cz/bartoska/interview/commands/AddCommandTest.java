package cz.bartoska.interview.commands;

import cz.bartoska.interview.Commands.AddCommand;
import cz.bartoska.interview.model.WeightPostCodePair;
import cz.bartoska.interview.repository.PostcodeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class AddCommandTest {

    private AddCommand addCommand;
    private PostcodeRepository postcodeRepository;

    @Before
    public void init(){
        postcodeRepository = new PostcodeRepository();
        addCommand = new AddCommand(postcodeRepository);
    }

    @Test
    public void executeAction() {
        addCommand.execute("10 100000");

        List<WeightPostCodePair> sortedRecords = postcodeRepository.getSortedRecords();
        assertEquals(1, sortedRecords.size());
        assertEquals(new BigDecimal("10"), sortedRecords.get(0).getWeight());
        assertEquals("100000", sortedRecords.get(0).getPostCode());
    }
}

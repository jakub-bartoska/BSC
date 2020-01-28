package cz.bartoska.interview.commands;

import cz.bartoska.interview.Commands.FeesFromFileCommand;
import cz.bartoska.interview.model.WeightFeePair;
import cz.bartoska.interview.repository.FeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class FeesFromFileCommandTest {

    private FeesFromFileCommand feesFromFileCommand;
    private FeeRepository feeRepository;

    @Before
    public void init(){
        feeRepository = new FeeRepository();
        feesFromFileCommand = new FeesFromFileCommand(feeRepository);
    }

    @Test
    public void executeAction() {
        feesFromFileCommand.execute("fee-file src/test/resources/commands/fee");

        List<WeightFeePair> sortedFees = feeRepository.getSortedFees();
        assertEquals(3, sortedFees.size());
        assertEquals(new BigDecimal("72.10"), sortedFees.get(0).getWeight());
        assertEquals(new BigDecimal("3"), sortedFees.get(0).getFee());
        assertEquals(new BigDecimal("5"), sortedFees.get(2).getWeight());
        assertEquals(new BigDecimal("1"), sortedFees.get(2).getFee());
    }
}

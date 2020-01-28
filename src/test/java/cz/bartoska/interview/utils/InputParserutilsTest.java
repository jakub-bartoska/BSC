package cz.bartoska.interview.utils;

import cz.bartoska.interview.model.WeightFeePair;
import cz.bartoska.interview.model.WeightPostCodePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class InputParserutilsTest {

    @Test
    public void inputToWeightPostPair() {
        WeightPostCodePair weightPostCodePair = InputParserutils.inputToWeightPostPair("10.5 12345");

        assertEquals(weightPostCodePair.getPostCode(), "12345");
        assertEquals(weightPostCodePair.getWeight(), new BigDecimal("10.5"));
    }

    @Test
    public void inputToFile() {
        InputParserutils.inputToFile("command src/test/resources/commands/dummy-file");
    }

    @Test
    public void inputToWeightFeePair() {
        WeightFeePair weightFeePair = InputParserutils.inputToWeightFeePair("10.5 11.5");

        assertEquals(weightFeePair.getWeight(), new BigDecimal("10.5"));
        assertEquals(weightFeePair.getFee(), new BigDecimal("11.5"));
    }
}

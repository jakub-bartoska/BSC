package cz.bartoska.interview.Commands;

import cz.bartoska.interview.model.WeightFeePair;
import cz.bartoska.interview.model.WeightPostCodePair;
import cz.bartoska.interview.repository.FeeRepository;
import cz.bartoska.interview.repository.PostcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Prints result in format:
 * <postal code: fixed 5 digits><space><total weight: fixed 3 decimal places, . (dot) as decimal separator><space>
 * <total fee: fixed 2 decimal places, . (dot) as decimal separator)
 */
@Service
public class PrintResultCommand extends BaseCommand {

    private final PostcodeRepository postcodeRepository;
    private final FeeRepository feeRepository;

    @Autowired
    public PrintResultCommand(PostcodeRepository postcodeRepository, FeeRepository feeRepository) {
        super("print", "Prints result in format:\n" +
                " <postal code: fixed 5 digits><space><total weight: fixed 3 decimal places, . (dot) as decimal separator><space>\n" +
                " <total fee: fixed 2 decimal places, . (dot) as decimal separator)");
        this.postcodeRepository = postcodeRepository;
        this.feeRepository = feeRepository;
    }

    @Override
    public void execute(String input) {
        List<WeightPostCodePair> sortedRecords = postcodeRepository.getSortedRecords();
        List<WeightFeePair> sortedFees = feeRepository.getSortedFees();
        sortedRecords.forEach(weightPostCodePair -> {
            String oututLine = weightPostCodePair.getPostCode() + " "
                    + weightPostCodePair.getWeight().setScale(3, RoundingMode.CEILING);
            oututLine = addFee(oututLine, sortedFees, weightPostCodePair.getWeight());
            System.out.println(oututLine);
        });
    }

    public void execute(){
        execute(null);
    }

    private BigDecimal getProperFee(BigDecimal weight, List<WeightFeePair> sortedFees) {
        return sortedFees
                .stream()
                .filter(fee -> weight.compareTo(fee.getWeight()) >= 0)
                .map(WeightFeePair::getFee)
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }

    private String addFee(String oututLine, List<WeightFeePair> sortedFees, BigDecimal weight) {
        if (!sortedFees.isEmpty()) {
            return oututLine + " " + getProperFee(weight, sortedFees).setScale(2, RoundingMode.CEILING);
        }
        return oututLine;
    }
}

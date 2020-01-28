package cz.bartoska.interview.Commands;

import cz.bartoska.interview.model.WeightPostCodePair;
import cz.bartoska.interview.repository.PostcodeRepository;
import cz.bartoska.interview.utils.InputParserutils;
import cz.bartoska.interview.validators.AddCommandValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Default command. Adds weight to postcode. If there is already some weight, new weight is added to old one.
 * Format of line is:
 * <weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator>
 * <space><fee: positive number, >=0, fixed two decimals, . (dot) as decimal separator>
 */
@Service
public class AddCommand extends BaseCommand {

    private final PostcodeRepository postcodeRepository;

    @Autowired
    public AddCommand(PostcodeRepository postcodeRepository) {
        super("", "Default command. Adds weight to postcode. If there is already some weight, new weight is added to old one.\n" +
                        " Format of line is:\n" +
                        " <weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator>\n" +
                        " <space><fee: positive number, >=0, fixed two decimals, . (dot) as decimal separator>",
                new AddCommandValidator());
        this.postcodeRepository = postcodeRepository;
    }

    @Override
    public void execute(String input) {
        WeightPostCodePair weightPostCodePair = InputParserutils.inputToWeightPostPair(input);
        postcodeRepository.addWeight(weightPostCodePair);
    }
}

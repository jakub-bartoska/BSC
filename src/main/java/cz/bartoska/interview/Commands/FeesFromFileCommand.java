package cz.bartoska.interview.Commands;

import cz.bartoska.interview.model.WeightFeePair;
import cz.bartoska.interview.repository.FeeRepository;
import cz.bartoska.interview.utils.InputParserutils;
import cz.bartoska.interview.validators.FeeCommandValidator;
import cz.bartoska.interview.validators.FeesFromFileCommandValidator;
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
 * Loads file which contains weights and fees. Data inside this file are used for adding fees to each post code.
 * Format for fee row is:
 * <weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space>
 * <fee: positive number, >=0, fixed two decimals, . (dot) as decimal separator>
 * Format of this command is:
 * add-file <path-to-file>
 */
@Service
public class FeesFromFileCommand extends BaseCommand {

    Logger logger = LoggerFactory.getLogger(FeesFromFileCommand.class);

    private final FeeRepository feeRepository;

    @Autowired
    public FeesFromFileCommand(FeeRepository feeRepository) {
        super("fee-file", "Loads file which contains weights and fees. Data inside this file are used for adding fees to each post code.\n" +
                " Format for fee row is:\n" +
                " <weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space>\n" +
                " <fee: positive number, >=0, fixed two decimals, . (dot) as decimal separator>\n" +
                " Format of this command is:\n" +
                " add-file <path-to-file>", new FeesFromFileCommandValidator());
        this.feeRepository = feeRepository;
    }

    @Override
    public void execute(String input) {
        File file = InputParserutils.inputToFile(input);
        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()))) {
            FeeCommandValidator feeCommandValidator = new FeeCommandValidator();
            stream.forEach(line -> {
                try{
                    feeCommandValidator.validate(line);
                } catch (ValidationException e){
                    logger.error("line '{}' was not properly formated, so it is being skipped", line);
                    return;
                }
                WeightFeePair weightFeePair = InputParserutils.inputToWeightFeePair(line);
                feeRepository.addFee(weightFeePair);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package cz.bartoska.interview.Commands;

import cz.bartoska.interview.validators.QuitCommandValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Stops application.
 */
@Service
public class QuitCommand extends BaseCommand {

    Logger logger = LoggerFactory.getLogger(QuitCommand.class);
    private ApplicationContext applicationContext;

    @Autowired
    public QuitCommand(ApplicationContext applicationContext) {
        super("quit", "Stops application.", new QuitCommandValidator());
        this.applicationContext = applicationContext;
    }

    @Override
    public void execute(String input) {
        logger.info("Stopping application.");
        SpringApplication.exit(applicationContext, () -> 0);
    }
}

package cz.bartoska.interview.service;

import cz.bartoska.interview.Commands.PrintResultCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Scheduler
 */
@Service
public class Scheduler {

    private final PrintResultCommand printResultCommand;

    @Autowired
    public Scheduler(PrintResultCommand printResultCommand) {
        this.printResultCommand = printResultCommand;
    }

    @Scheduled(fixedRate = 30000)
    public void printWeights() {
        printResultCommand.execute("");
    }
}

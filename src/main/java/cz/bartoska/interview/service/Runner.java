package cz.bartoska.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Scanner;

/**
 * implementation of command line runner.
 */
@Service
public class Runner implements CommandLineRunner {

    private CommandExecutor commandExecutor;

    @Autowired
    public Runner(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        while (true) {
            String line = scanner.nextLine();
            commandExecutor.executeProperCommand(line);
        }
    }


}

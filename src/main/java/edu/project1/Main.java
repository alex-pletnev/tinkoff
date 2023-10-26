package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MAX_ATTEMPTS = 5;

    private Main() {
    }

    public static void main(String[] args) {
        Session session = new Session(MAX_ATTEMPTS);

        try (Reader reader = new Reader()) {
            ConsoleHangman consoleHangman = new ConsoleHangman(LOGGER, session, reader);
            consoleHangman.run();
        } catch (Exception e) {
            LOGGER.error("Problem with 'scanner close'", e);
        }
    }
}

package edu.project1;

import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private final Logger logger;
    private final Session session;
    private final Reader reader;

    public ConsoleHangman(Logger logger, Session session, Reader reader) {
        this.logger = logger;
        this.session = session;
        this.reader = reader;
    }

    boolean run() {
        Printer printer = new Printer();
        printer.printWellComeMessage(logger);
        while (true) {
            try {
                printer.printGuessALetter(logger);
                char newLetter = reader.readLetter(logger);
                if (session.checkAnswer(newLetter)) {
                    printer.printHit(logger);
                } else {
                    printer.printMistake(logger, session);
                }
                printer.printWord(logger, session);
                if (session.isWin()) {
                    printer.printWon(logger);
                    return true;

                }
                if (session.isLoose()) {
                    printer.printLost(logger, session);
                    return false;
                }

            } catch (ExitException e) {
                break;
            }
        }
        return false;

    }
}

package edu.project1;

import org.apache.logging.log4j.Logger;

public class ConsoleHangman {

    boolean run(Logger logger, Session session, Reader reader) {
        Printer printer = new Printer();
        while (true) {
            try {
                printer.printGuessALetter(logger);
                char newLetter = reader.readLetter();
                if (session.checkAnswer(newLetter)) {
                    printer.printHit(logger);
                    printer.printWord(logger, session);
                } else {
                    printer.printMistake(logger, session);
                    printer.printWord(logger, session);
                }
                if (session.isWin()) {
                    printer.printWon(logger);
                    return true;

                }
                if (session.isLoose()) {
                    printer.printLost(logger);
                    return false;
                }

            } catch (ExitException e) {
                break;
            }
        }
        return false;

    }
}

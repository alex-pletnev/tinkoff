package edu.project1;

import org.apache.logging.log4j.Logger;
import java.util.zip.ZipEntry;

public class Printer {

    void printWellComeMessage(Logger logger) {
        logger.info("Welcome\nIf you want to end the game in advance, write an 'exit'");
    }

    void printGuessALetter(Logger logger) {
        logger.info("Guess a letter:");
    }

    void printWord(Logger logger, Session session) {
        StringBuilder out = new StringBuilder("The word: ");
        for (char c : session.getCurrentAnswer()) {
            out.append(c);
        }
        logger.info(out);
    }

    void printHit(Logger logger) {
        logger.info("Hit!");
    }

    void printMistake(Logger logger, Session session) {
        logger.info("Missed, mistake {} out of {}.", session.getAttempts(), session.getMaxAttempts());
    }

    void printLost(Logger logger, Session session) {
        String answer = new String(session.getAnswer());
        logger.info("You lost!");
        logger.info("Answer is '{}'", answer);
    }

    void printWon(Logger logger) {
        logger.info("You won!");
    }


}

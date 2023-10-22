package edu.project1;

import org.apache.logging.log4j.Logger;

public class Printer {
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
        logger.info("Missed, mistake " + session.getAttempts() + " out of " + session.getMaxAttempts() + ".");
    }

    void printLost(Logger logger) {
        logger.info("You lost!");
    }

    void printWon(Logger logger) {
        logger.info("You won!");
    }


}

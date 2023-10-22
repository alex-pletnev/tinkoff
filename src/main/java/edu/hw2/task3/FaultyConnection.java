package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final int RANDOM_LIMIT = 10;

    @Override
    public void execute(String command, Logger logger) {
        int randomInt;
        Random random = new Random();
        randomInt = random.nextInt(RANDOM_LIMIT);
        if (randomInt == 0) {
            throw new ConnectionException("Failed to establish connection");
        }
        logger.info(command);
    }
}

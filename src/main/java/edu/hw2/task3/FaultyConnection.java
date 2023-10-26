package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final int RANDOM_LIMIT = 10;
    private final Logger logger = LogManager.getLogger();

    @Override
    public void execute(String command) {
        int randomInt;
        Random random = new Random();
        randomInt = random.nextInt(RANDOM_LIMIT);
        if (randomInt == 0) {
            throw new ConnectionException("Failed to establish connection");
        }
        logger.info(command);
    }

    @Override
    public void close() throws Exception {
        logger.info("Connection closed");
    }
}

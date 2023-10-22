package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private final Logger logger = LogManager.getLogger();

    @Override
    public void execute(String command, Logger logger) {
        logger.info(command);
    }

    @Override
    public void close() throws Exception {
        logger.info("Connection closed");
    }
}

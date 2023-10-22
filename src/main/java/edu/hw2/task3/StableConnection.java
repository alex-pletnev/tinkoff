package edu.hw2.task3;

import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    @Override
    public void execute(String command, Logger logger) {
        logger.info(command);
    }
}

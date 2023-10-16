package edu.hw1;

import org.apache.logging.log4j.Logger;

public class Task0 {
    private final Logger logger;

    public Task0(Logger logger) {
        this.logger = logger;
    }

    public void run() {
        logger.info("Привет, мир!");
    }
}

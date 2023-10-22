package edu.hw2.task3;

import org.apache.logging.log4j.Logger;

public interface Connection extends AutoCloseable {
    void execute(String command, Logger logger);
}

package edu.hw2.task3;

import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;
    private final Logger logger;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts, Logger logger) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
        this.logger = logger;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) throws ConnectionException {
        int attempts = 0;
        while (attempts < maxAttempts) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command, logger);
                return;
            } catch (ConnectionException e) {
                attempts++;
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        throw new ConnectionException("Failed to execute commands '" + command
            + "' after " + maxAttempts + " attempts");
    }
}

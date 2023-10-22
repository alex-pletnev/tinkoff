package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final int RANDOM_LIMIT = 10;

    @Override
    public Connection getConnection() {
        Random random = new Random();
        if (random.nextInt(RANDOM_LIMIT) == 0) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}

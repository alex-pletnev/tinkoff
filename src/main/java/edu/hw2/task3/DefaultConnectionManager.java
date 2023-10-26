package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final int RANDOM_LIMIT = 10;
    private final Random random;

    public DefaultConnectionManager(Random random) {
        this.random = random;
    }

    public DefaultConnectionManager() {
        this(new Random());
    }

    @Override
    public Connection getConnection() {
        if (random.nextInt(RANDOM_LIMIT) == 0) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }

}

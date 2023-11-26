package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadToPiCounter extends Thread {
    private static final double SQUARE_SIZE = 10;
    private static final double CONSTANT = 4;
    private double circleCount = 0;
    private double totalCount = 0;
    private final double x0 = SQUARE_SIZE / 2;
    private final double y0 = SQUARE_SIZE / 2;
    private final double r = SQUARE_SIZE / 2;
    private final int iterationCount;

    public ThreadToPiCounter(int iterationCount) {
        this.iterationCount = iterationCount;
    }

    @Override
    public void run() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (int i = 0; i < iterationCount; i++) {
            double x = threadLocalRandom.nextDouble(SQUARE_SIZE);
            double y = threadLocalRandom.nextDouble(SQUARE_SIZE);
            if (Math.pow((x - x0), 2) + Math.pow((y - y0), 2) <= Math.pow(r, 2)) {
                circleCount++;
            }
            totalCount++;
        }
    }

    public double getPi() {
        return CONSTANT * (circleCount / totalCount);
    }
}

package edu.hw7.task4;

import java.util.Random;

public class PiOneThreadCounter {

    private static final double SQUARE_SIZE = 10;
    private static final double CONSTANT = 4;
    private final Random random;

    public PiOneThreadCounter() {
        this.random = new Random();
    }

    public double piCount(int n) {
        if (n == 0) {
            return 0;
        }
        double totalCount = 0;
        double circleCount = 0;

        // координаты центра круга
        double x0 = SQUARE_SIZE / 2;
        double y0 = SQUARE_SIZE / 2;
        //радиус окружности
        double r = SQUARE_SIZE / 2;
        for (int i = 0; i < n; i++) {
            double x = random.nextDouble(SQUARE_SIZE);
            double y = random.nextDouble(SQUARE_SIZE);
            if (Math.pow((x - x0), 2) + Math.pow((y - y0), 2) <= Math.pow(r, 2)) {
                circleCount++;
            }
            totalCount++;
        }
        return CONSTANT * (circleCount / totalCount);
    }
}

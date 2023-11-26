package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;

public class PiMultiThreadCounter {
    private static final int CORE = 7;

    public double piCount(int n) throws InterruptedException {
        if (n == 0) {
            return 0;
        }
        var iterationCount = n / CORE;

        List<ThreadToPiCounter> piCounterList = new ArrayList<>();
        for (int i = 0; i < CORE; i++) {
            piCounterList.add(new ThreadToPiCounter(iterationCount));
        }
        for (int i = 0; i < CORE; i++) {
            piCounterList.get(i).start();
        }
        for (int i = 0; i < CORE; i++) {
            piCounterList.get(i).join();
        }

        double result = 0;
        for (int i = 0; i < CORE; i++) {
            result += piCounterList.get(i).getPi();
        }
        return result / CORE;
    }
}

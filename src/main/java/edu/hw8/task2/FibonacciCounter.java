package edu.hw8.task2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FibonacciCounter {
    private static final Logger LOGGER = LogManager.getLogger();
    private final int numThreads;

    public FibonacciCounter(int numThreads) {

        this.numThreads = numThreads;
    }

    public List<Integer> countFirstN(int n) {
        var fixedThreadPool = FixedThreadPool.create(numThreads);
        AtomicInteger counter = new AtomicInteger(0);
        var ans = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            int finalI = i;
            fixedThreadPool.execute(() -> {
                int result = fibonacci(finalI);
                LOGGER.info("Fibonacci({}) = {}", finalI, result);
                ans.add(result);
                counter.incrementAndGet();
            });
        }

        while (counter.get() < n) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                LOGGER.error(e);
            }
        }

        try {
            fixedThreadPool.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ans.sort(Comparator.naturalOrder());
        return ans;

    }

    private int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

}

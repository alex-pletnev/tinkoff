package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    public int race(int threadCount) throws InterruptedException {
        var atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(() -> atomicInteger.set(atomicInteger.incrementAndGet()));
            t.start();
            t.join();
        }
        return atomicInteger.get();
    }
}

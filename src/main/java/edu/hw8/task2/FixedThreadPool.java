package edu.hw8.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedThreadPool implements ThreadPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Thread[] threads;

    private FixedThreadPool(Thread[] threads) {
        this.threads = threads;
    }

    public static FixedThreadPool create(int size) {
        Thread[] threads = new Thread[size];
        return new FixedThreadPool(threads);
    }

    @Override
    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        for (Thread thread : threads) {
            thread = new Thread(runnable);
            thread.start();
            return;
        }
        LOGGER.info("All threads are busy. Task cannot be executed.");
    }

    @Override
    public void close() {
        for (Thread thread : threads) {
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        }
    }
}

package edu.hw7.task2;

import java.util.stream.IntStream;

public class Task2 {

    public int parallelFactorial(int num) {
        return IntStream
            .rangeClosed(1, num)
            .parallel()
            .reduce(1, (a, b) -> a * b);
    }
}

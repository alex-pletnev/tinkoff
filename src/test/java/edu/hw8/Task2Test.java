package edu.hw8;

import edu.hw8.task2.FibonacciCounter;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Task2Test {

    private FibonacciCounter fibonacciCounter;

    @BeforeEach
    public void setUp() {
        fibonacciCounter = new FibonacciCounter(5);
    }

    @Test
    void countFirstNTest() {
        int n = 10;
        List<Integer> excepted = Lists.list(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);

        var actual = fibonacciCounter.countFirstN(n);
        Assertions.assertEquals(excepted, actual);
    }
}

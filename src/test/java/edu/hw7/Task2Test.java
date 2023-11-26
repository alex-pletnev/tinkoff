package edu.hw7;

import edu.hw7.task2.Task2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task2Test {

    Task2 task2;

    @BeforeEach
    public void setUp() {
        task2 = new Task2();
    }


    @ParameterizedTest
    @CsvSource({"0, 1", "1, 1", "2, 2", "3, 6", "5, 120", "12, 479001600"})
    void parallelFactorialTest(int num, int excepted) {
        var actual = task2.parallelFactorial(num);
        Assertions.assertEquals(excepted, actual);
    }
}

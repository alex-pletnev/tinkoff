package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task2Test {
    @ParameterizedTest
    @CsvSource({"0, 1,", "1234567890, 10", "22, 2", "333, 3", "4666, 4", "544, 3"})
    void countDigitsTest(int num, int expected) {
        Task2 task2 = new Task2();
        int count = task2.countDigits(num);
        Assertions.assertEquals(expected, count);
    }
}

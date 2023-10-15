package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task6Test {
    @ParameterizedTest
    @CsvSource({"6621, 5", "6554, 4", "1234, 3", "6174, 1"})
    void countKTest(int number, int excepted) {
        Task6 task6 = new Task6();
        int ans = task6.countK(number);
        Assertions.assertEquals(excepted, ans);
    }
}

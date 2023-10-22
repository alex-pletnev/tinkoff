package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task5Test {
    @ParameterizedTest
    @CsvSource({"11211230, true",
        "13001120, true",
        "23336014, true",
        "11, true",
        "123312, true",
        "1, false",
        "12345, false"})
    void isPalindromeDescendantTest(int number, boolean excepted) {
        Task5 task5 = new Task5();
        boolean ans = task5.isPalindromeDescendant(number);
        Assertions.assertEquals(excepted, ans);
    }
}

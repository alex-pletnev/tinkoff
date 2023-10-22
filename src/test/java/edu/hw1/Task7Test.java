package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task7Test {
    @ParameterizedTest
    @CsvSource({"16, 1, 1", "17, 2, 6", "42, 1, 21", "16, 6, 1", "1, 1, 1", "3, 100, 3", "8, -1, 4"})
    void rotateLeftTest(int n, int shift, int expected) {
        Task7 task7 = new Task7();
        int ans = task7.rotateLeft(n, shift);
        Assertions.assertEquals(expected, ans);
    }

    @ParameterizedTest
    @CsvSource({"8, 1, 4", "10, 1, 5", "10, 5, 5", "10, 6, 10", "127, 100, 127", "8, -1, 1"})
    void rotateRightTest(int n, int shift, int expected) {
        Task7 task7 = new Task7();
        int ans = task7.rotateRight(n, shift);
        Assertions.assertEquals(expected, ans);
    }

}

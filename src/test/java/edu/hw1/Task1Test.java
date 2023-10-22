package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

 class Task1Test {
    @ParameterizedTest
    @CsvSource({"'00:00', 0", "'60:59', 3659", "'01:00', 60",
        "'13:56', 836", "'10:60', -1", "'999:59', 59999", "'-1:25', -1"})
    void testMinutesToSeconds(String input, int expected) {
        Task1 task1 = new Task1();
        int ans = task1.minutesToSeconds(input);
        Assertions.assertEquals(expected, ans);
    }
}

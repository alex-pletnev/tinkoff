package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task4Test {
    @ParameterizedTest
    @CsvSource({"'123456', '214365'",
        "'hTsii  s aimex dpus rtni.g', 'This is a mixed up string.'",
        "'badce', 'abcde'", "'a', 'a'", "'ab', 'ba'"})
    void fixStringTest(String input, String expected) {
        Task4 task4 = new Task4();
        String ans = task4.fixString(input);
        Assertions.assertEquals(expected, ans);
    }
}

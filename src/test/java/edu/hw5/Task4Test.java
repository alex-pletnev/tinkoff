package edu.hw5;

import edu.hw5.task4.Task4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task4Test {

    private Task4 task4;

    @BeforeEach
    void setUp() {
        task4 = new Task4();
    }

    @ParameterizedTest
    @CsvSource({"'qwerty!2', true",
        "'zasdgt!@^', true",
        "'@', true",
        "'saffhrndfbdfve112454232', false",
        "'', false",
        ",false"})
    void hasSpecialSymbolsTest(String password, boolean excepted) {
        var actual = task4.hasSpecialSymbols(password);
        Assertions.assertEquals(excepted, actual);
    }
}

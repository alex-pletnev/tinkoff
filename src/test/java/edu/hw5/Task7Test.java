package edu.hw5;

import edu.hw5.task7.Task7;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task7Test {

    Task7 task7;

    @BeforeEach
    void setUp() {
        task7 = new Task7();
    }

    @ParameterizedTest
    @CsvSource({"'1101', true",
        "'000', true",
        "'001', false",
        "'10', false",
        "'020', false",
        "'', false",
        " , false"
    })
    @DisplayName("содержит не менее 3 символов, причем третий символ равен 0")
    void hasMatchesWithRegex1Test(String string, boolean excepted) {
        var actual = task7.hasMatchesWithRegex1(string);
        Assertions.assertEquals(excepted, actual);
    }

    @ParameterizedTest
    @CsvSource({"'1101', true",
        "'000', true",
        "'001', false",
        "'10', false",
        "'020', false",
        "'', false",
        " , false"
    })
    @DisplayName("начинается и заканчивается одним и тем же символом")
    void hasMatchesWithRegex2Test(String string, boolean excepted) {
        var actual = task7.hasMatchesWithRegex2(string);
        Assertions.assertEquals(excepted, actual);
    }

    @ParameterizedTest
    @CsvSource({"'11', true",
        "'0', true",
        "'001', true",
        "'1000', false",
        "'020', false",
        "'', false",
        " , false"
    })
    @DisplayName("длина не менее 1 и не более 3")
    void hasMatchesWithRegex3Test(String string, boolean excepted) {
        var actual = task7.hasMatchesWithRegex3(string);
        Assertions.assertEquals(excepted, actual);
    }

}

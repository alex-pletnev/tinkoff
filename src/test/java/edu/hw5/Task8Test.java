package edu.hw5;

import edu.hw5.task8.Task8;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task8Test {

    Task8 task8;

    @BeforeEach
    void setUp() {
        task8 = new Task8();
    }

    @ParameterizedTest
    @CsvSource({"'1', true",
        "'000', true",
        "'10100', true",
        "'111', true",
        "'11', false",
        "'00', false",
        "'1010', false",
        "'101101', false",
        "'0001', false",
        "'020', false",
        "'', false",
        " , false"
    })
    @DisplayName("нечетной длины")
    void hasMatchesWithRegex1Test(String string, boolean excepted) {
        var actual = task8.hasMatchesWithRegex1(string);
        Assertions.assertEquals(excepted, actual);
    }

    @ParameterizedTest
    @CsvSource({"'0', true",
        "'011', true",
        "'00000', true",
        "'0101110', true",
        "'11', true",
        "'1111', true",
        "'1010101010', true",
        "'00', false",
        "'0101', false",
        "'0001', false",
        "'0111', false",
        "'1', false",
        "'111', false",
        "'1010101', false",
        "'10101101011', false",
        "'020', false",
        "'', false",
        " , false"
    })
    @DisplayName("начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину")
    void hasMatchesWithRegex2Test(String string, boolean excepted) {
        var actual = task8.hasMatchesWithRegex2(string);
        Assertions.assertEquals(excepted, actual);
    }

    @ParameterizedTest
    @CsvSource({"'000', true",
        "'0010', true",
        "'101010000', true",
        "'10001000101010', true",
        "'0', false",
        "'1010010', false",
        "'10101000100', false",
        "'00', false",
        "'111', false",
        "'020', false",
        "'', true",
        " , false"
    })
    @DisplayName("количество 0 кратно 3")
    void hasMatchesWithRegex3Test(String string, boolean excepted) {
        var actual = task8.hasMatchesWithRegex3(string);
        Assertions.assertEquals(excepted, actual);
    }

    @ParameterizedTest
    @CsvSource({"'1', true",
        "'1110', true",
        "'1111', true",
        "'1101010101001', true",
        "'101001010101', true",
        "'011110101', true",
        "'11111', true",
        "'11', false",
        "'111', false",
        "'020', false",
        "'', true",
        " , false"
    })
    @DisplayName("любая строка, кроме 11 или 111")
    void hasMatchesWithRegex4Test(String string, boolean excepted) {
        var actual = task8.hasMatchesWithRegex4(string);
        Assertions.assertEquals(excepted, actual);
    }

    @ParameterizedTest
    @CsvSource({"'1', true",
        "'111', true",
        "'1010101', true",
        "'1011101', true",
        "'010', false",
        "'100', false",
        "'001', false",
        "'101010100', false",
        "'111111110', false",
        "'020', false",
        "'', false",
        " , false"
    })
    @DisplayName("каждый нечетный символ равен 1")
    void hasMatchesWithRegex5Test(String string, boolean excepted) {
        var actual = task8.hasMatchesWithRegex5(string);
        Assertions.assertEquals(excepted, actual);
    }

    @ParameterizedTest
    @CsvSource({"'001', true",
        "'010', true",
        "'100', true",
        "'00', true",
        "'000100', true",
        "'110', false",
        "'10', false",
        "'00101', false",
        "'1010110100', false",
        "'020', false",
        "'', false",
        " , false"
    })
    @DisplayName("содержит не менее двух 0 и не более одной 1")
    void hasMatchesWithRegex6Test(String string, boolean excepted) {
        var actual = task8.hasMatchesWithRegex6(string);
        Assertions.assertEquals(excepted, actual);
    }

    @ParameterizedTest
    @CsvSource({"'1', true",
        "'0', true",
        "'0101', true",
        "'101010101', true",
        "'000010101010100', true",
        "'00000', true",
        "'11', false",
        "'010110100', false",
        "'0000011', false",
        "'020', false",
        "'', true",
        " , false"
    })
    @DisplayName("нет последовательных 1")
    void hasMatchesWithRegex7Test(String string, boolean excepted) {
        var actual = task8.hasMatchesWithRegex7(string);
        Assertions.assertEquals(excepted, actual);
    }
}

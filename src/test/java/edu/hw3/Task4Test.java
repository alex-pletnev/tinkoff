package edu.hw3;

import edu.hw3.task4.InvalidNumberValueException;
import edu.hw3.task4.Task4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task4Test {

    @ParameterizedTest
    @CsvSource({"2, 'II'", "12, 'XII'", "16, 'XVI'", "623, 'DCXXIII'"})
    void convertToRomanWithZeroCorrectNumber(int arabic, String excepted) {
        Task4 task4 = new Task4();
        String roman = task4.convertToRoman(arabic);
        Assertions.assertEquals(excepted, roman);
    }

    @Test
    void testConvertToRomanWithZero() {
        Task4 task4 = new Task4();
        Assertions.assertThrows(InvalidNumberValueException.class, () -> task4.convertToRoman(0));
    }

    @Test
    void testConvertToRomanWithNegativeNumber() {
        Task4 task4 = new Task4();
        Assertions.assertThrows(InvalidNumberValueException.class, () -> task4.convertToRoman(-1));
    }

    @Test
    void testConvertToRomanWithLargeNumber() {
        Task4 task4 = new Task4();
        Assertions.assertThrows(InvalidNumberValueException.class, () -> task4.convertToRoman(4000));
    }
}

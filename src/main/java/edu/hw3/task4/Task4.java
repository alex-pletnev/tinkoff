package edu.hw3.task4;

public class Task4 {
    private static final String[] KEYS
        = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int[] VALUES
        = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final int NUMBER_OF_REPLAY = 3;

    public String convertToRoman(int arabic) {
        if (arabic <= 0) {
            throw new InvalidNumberValueException("The number must be positive");
        }

        var currentArabic = arabic;
        StringBuilder roman = new StringBuilder();
        int index = 0;
        while (currentArabic > 0) {
            var count = currentArabic / VALUES[index];
            if (count > NUMBER_OF_REPLAY) {
                throw new InvalidNumberValueException("The number's too big");
            }
            for (int i = 0; i < count; i++) {
                roman.append(KEYS[index]);
                currentArabic -= VALUES[index];
            }
            index++;
        }
        return roman.toString();
    }
}

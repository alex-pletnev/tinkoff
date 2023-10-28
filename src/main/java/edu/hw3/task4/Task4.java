package edu.hw3.task4;

public class Task4 {

    public String convertToRoman(int arabic) {
        if (arabic <= 0) {
            throw new InvalidNumberValueException("The number must be positive");
        }

        String[] keys = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        var currentArabic = arabic;
        StringBuilder roman = new StringBuilder();
        int index = 0;
        while (currentArabic > 0) {
            var count = currentArabic / values[index];
            if (count > 3) {
                throw new InvalidNumberValueException("The number's too big");
            }
            for (int i = 0; i < count; i++) {
                roman.append(keys[index]);
                currentArabic -= values[index];
            }
            index++;
        }
        return roman.toString();
    }
}

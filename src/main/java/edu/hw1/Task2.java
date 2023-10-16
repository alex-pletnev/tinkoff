package edu.hw1;

public class Task2 {
    static final int PREVIOUS_NUMBER_ORDER = 10;

    public int countDigits(int num) {
        int div;
        int digits = 0;
        do {
            div = num / PREVIOUS_NUMBER_ORDER;
            digits++;
            num /= PREVIOUS_NUMBER_ORDER;
        } while (div != 0);
        return digits;
    }
}

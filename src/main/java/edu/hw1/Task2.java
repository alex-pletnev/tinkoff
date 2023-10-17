package edu.hw1;

public class Task2 {
    static final int PREVIOUS_NUMBER_ORDER = 10;

    public int countDigits(int number) {
        int num = number;
        int digits = 0;
        do {
            num /= PREVIOUS_NUMBER_ORDER;
            digits++;
        } while (num != 0);
        return digits;
    }
}

package edu.hw1;

public class Task2 {
    static final int NEXT_NUMBER_ORDER = 10;

    public int countDigits(int num) {

        int divisor = NEXT_NUMBER_ORDER;
        int div;
        int digits = 0;
        do {
            div = num / divisor;
            digits++;
            divisor *= NEXT_NUMBER_ORDER;
        } while (div != 0);
        return digits;
    }
}

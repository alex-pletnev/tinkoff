package edu.hw1;

public class Task2 {
    public int countDigits(int num) {
        int divisor = 10;
        int div;
        int digits = 0;
        do {
            div = num / divisor;
            digits++;
            divisor *= 10;
        } while (div != 0);
        return digits;
    }
}

package edu.hw1;

import java.util.Arrays;

public class Task6 {
    static final int THOUSAND = 1000;
    static final int HUNDRED = 100;
    static final int TEN = 10;
    static final int SHIFT = 48;

    public int countK(int number) {
        int k = 0;
        int nextNumber = number;
        final int TARGET = 6174;
        do {
            k++;
            nextNumber = getDescending(nextNumber) - getAscending(nextNumber);
        } while (nextNumber != TARGET);
        return k;
    }

    private int getAscending(int number) {
        char[] chars = String.valueOf(number).toCharArray();
        Arrays.sort(chars);
        int i = 0;
        return THOUSAND * (chars[i++] - SHIFT)
            + HUNDRED * (chars[i++] - SHIFT)
            + TEN * (chars[i++] - SHIFT)
            + (chars[i] - SHIFT);
    }

    private int getDescending(int number) {
        char[] chars = String.valueOf(number).toCharArray();
        Arrays.sort(chars);
        reverseArray(chars);
        int i = 0;
        return THOUSAND * (chars[i++] - SHIFT)
            + HUNDRED * (chars[i++] - SHIFT)
            + TEN * (chars[i++] - SHIFT)
            + (chars[i] - SHIFT);
    }

    private static void reverseArray(char[] array) {
        int i = 0;
        int j = array.length - 1;

        while (i < j) {
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }

}

package edu.hw1;

import java.util.Arrays;

public class Task6 {
    public int countK(int number) {
        int k = 0;
        do {
            k++;
            number = getDescending(number) - getAscending(number);
        } while (number != 6174);
        return k;
    }

    private int getAscending(int number) {
        char[] chars = String.valueOf(number).toCharArray();
        Arrays.sort(chars);
        return 1000 * (chars[0] - 48) +
            100 * (chars[1] - 48) +
            10 * (chars[2] - 48) +
            (chars[3] - 48);
    }

    private int getDescending(int number) {
        char[] chars = String.valueOf(number).toCharArray();
        Arrays.sort(chars);
        reverseArray(chars);
        return 1000 * (chars[0] - 48) +
            100 * (chars[1] - 48) +
            10 * (chars[2] - 48) +
            (chars[3] - 48);
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

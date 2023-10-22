package edu.hw1;

import java.util.Arrays;
import java.util.Comparator;

public class Task6 {
    static final int TARGET = 6174;
    static final int SHIFT = 48;
    static final int NEXT_NUMBER_ORDER = 10;


    public int countK(int number) {
        return countKRecursive(number, 0);
    }

    private int countKRecursive(int number, int k) {
        int counter = k;
        counter++;
        int nextNumber = number;
        nextNumber = getDescending(nextNumber) - getAscending(nextNumber);
        if (nextNumber == TARGET) {
            return counter;
        }
        return countKRecursive(nextNumber, counter);
    }

    private int getAscending(int number) {
        Integer[] nums = fromIntToIntArray(number);
        int multiplier = 1;
        int ascendingNum = 0;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 0; i--) {
            ascendingNum += nums[i] * multiplier;
            multiplier *= NEXT_NUMBER_ORDER;
        }

        return ascendingNum;
    }

    private int getDescending(int number) {
        Integer[] nums = fromIntToIntArray(number);
        int multiplier = 1;
        int ascendingNum = 0;
        Arrays.sort(nums, Comparator.reverseOrder());
        for (int i = nums.length - 1; i >= 0; i--) {
            ascendingNum += nums[i] * multiplier;
            multiplier *= NEXT_NUMBER_ORDER;
        }

        return ascendingNum;
    }

    private Integer[] fromIntToIntArray(int number) {
        char[] chars = String.valueOf(number).toCharArray();
        Integer[] intArray = new Integer[chars.length];
        for (int i = 0; i < chars.length; i++) {
            intArray[i] = chars[i] - SHIFT;
        }
        return intArray;
    }

}

package edu.hw1;

public class Task3 {
    public boolean isNestable(int[] a1, int[] a2) {
        int a1min = findMin(a1);
        int a2min = findMin(a2);
        if (a1min <= a2min) {
            return false;
        }
        int a1max = findMax(a1);
        int a2max = findMax(a2);
        return a1max < a2max;
    }

    private int findMin(int[] a) {
        int min = Integer.MAX_VALUE;
        for (int i : a) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    private int findMax(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int i : a) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
}

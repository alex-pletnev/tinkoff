package edu.hw1;

public class Task7 {
    public int rotateLeft(int n, int shift) {
        if (shift < 0) {
            return rotateRight(n, Math.abs(shift));
        }
        char[] bin = Integer.toBinaryString(n).toCharArray();
        for (int i = 0; i < shift; i++) {
            char tmp = bin[0];
            for (int j = 1; j < bin.length; j++) {
                bin[j - 1] = bin[j];
            }
            bin[bin.length - 1] = tmp;
        }
        return Integer.parseInt(new String(bin), 2);
    }

    public int rotateRight(int n, int shift) {
        if (shift < 0) {
            return rotateLeft(n, Math.abs(shift));
        }
        char[] bin = Integer.toBinaryString(n).toCharArray();
        for (int i = 0; i < shift; i++) {
            char lastChar = bin[bin.length - 1];
            char firstChar = bin[0];
            for (int j = 0; j < bin.length - 1; j++) {
                char nextChar = bin[j + 1];
                bin[j + 1] = firstChar;
                firstChar = nextChar;
            }
            bin[0] = lastChar;
        }
        return Integer.parseInt(new String(bin), 2);
    }
}

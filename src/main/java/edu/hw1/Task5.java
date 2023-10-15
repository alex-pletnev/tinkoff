package edu.hw1;

public class Task5 {
    static final int SHIFT = 48;
    static final int SMALLEST_TWO_DIGIT = 10;
    public boolean isPalindromeDescendant(int number) {
        int descendant = number;
        while (!isPalindrome(descendant)) {
            descendant = getDescendant(descendant);
        }
        return descendant > SMALLEST_TWO_DIGIT;

    }

    private boolean isPalindrome(int number) {
        char[] charArray = String.valueOf(number).toCharArray();
        for (int i = 0; i < charArray.length / 2; i++) {
            if (charArray[i] != charArray[charArray.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    private int getDescendant(int number) {
        char[] charArray = String.valueOf(number).toCharArray();
        StringBuilder descendantString = new StringBuilder();
        for (int i = 0; i < charArray.length; i += 2) {
            if (charArray.length % 2 == 1 && i + 1 == charArray.length) {
                descendantString.append(charArray[i]);
                break;
            }
            descendantString.append((charArray[i] - SHIFT) + (charArray[i + 1] - SHIFT));
        }
        return Integer.parseInt(descendantString.toString());
    }
}

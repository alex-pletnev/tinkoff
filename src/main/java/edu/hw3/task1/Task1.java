package edu.hw3.task1;

import org.jetbrains.annotations.NotNull;

public class Task1 {
    private static final int LOWER_CASE_SHIFT = 97;
    private static final int UPPER_CASE_SHIFT = 65;
    private static final int ALPHABETS_LENGTH = 26;

    public String atbash(@NotNull String string) {
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (isLatinLetter(chars[i])) {
                chars[i] = getMirrorLetter(chars[i]);
            }
        }
        return new String(chars);
    }

    private char getMirrorLetter(char letter) {
        int shift = LOWER_CASE_SHIFT;
        if (Character.isUpperCase(letter)) {
            shift = UPPER_CASE_SHIFT;
        }
        int index = ALPHABETS_LENGTH - 1 - (letter - shift);
        return (char) (index + shift);
    }

    private boolean isLatinLetter(char letter) {
        int index = Character.toLowerCase(letter) - LOWER_CASE_SHIFT;
        return Character.isLetter(letter)
            && index >= 0
            && index < ALPHABETS_LENGTH - 1;
    }
}

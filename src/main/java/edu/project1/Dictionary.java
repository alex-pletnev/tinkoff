package edu.project1;

import java.util.Random;

public class Dictionary {
    private Dictionary() {
    }

    private static final String[] DICTIONARY = {"apple", "guess", "origins", "hello"};

    public static String randomWord() {
        Random random = new Random();
        int i = Math.abs(random.nextInt()) % DICTIONARY.length;
        return DICTIONARY[i];
    }

}

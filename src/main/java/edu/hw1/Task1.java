package edu.hw1;

public class Task1 {
    static final int SECOND_LIMIT = 60;

    public int minutesToSeconds(String input) {
        String[] split = input.split(":");
        int min = Integer.parseInt(split[0]);
        int sek = Integer.parseInt(split[1]);

        if (sek >= SECOND_LIMIT || min < 0 || sek < 0) {
            return -1;
        }

        return min * SECOND_LIMIT + sek;

    }
}

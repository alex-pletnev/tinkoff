package edu.hw1;

public class Task1 {
    public int minutesToSeconds(String input) {
        final int SECOND_LIMIT = 60;
        int min = Integer.parseInt(input.split(":")[0]);
        int sek = Integer.parseInt(input.split(":")[1]);
        if (sek >= SECOND_LIMIT) {
            return -1;
        }
        return min * SECOND_LIMIT + sek;

    }
}

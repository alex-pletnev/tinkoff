package edu.hw1;

public class Task1 {
    public int minutesToSeconds(String input) {
        int min = Integer.parseInt(input.split(":")[0]);
        int sek = Integer.parseInt(input.split(":")[1]);
        if (sek >= 60) {
            return -1;
        }
        return min * 60 + sek;

    }
}

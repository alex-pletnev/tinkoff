package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    public String[] clusterize(String string) {
        int index = 0;
        List<String> clusters = new ArrayList<>();
        while (index < string.length()) {
            String newCluster = findCluster(string.toCharArray(), "", index);
            clusters.add(newCluster);
            index += newCluster.length();
        }
        return clusters.toArray(new String[0]);
    }
    private String findCluster(char[] chars, String currentCluster, int index) {
        if (index >= chars.length
            || (chars[index] != '(' && chars[index] != ')')) {
            throw new UnclusterizedStringException();
        }
        currentCluster += chars[index];
        if (countChars(currentCluster, '(') == countChars(currentCluster, ')')) {
            return currentCluster;
        }
        return findCluster(chars, currentCluster, ++index);
    }
    private int countChars(String string, char c) {
        char[] chars = string.toCharArray();
        int count = 0;
        for (char i : chars) {
            if (c == i) {
                count++;
            }
        }
        return count;
    }
}

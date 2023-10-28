package edu.hw3.task2;

import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private String tmpCluster;

    public String[] clusterize(String string) {
        int index = 0;
        List<String> clusters = new ArrayList<>();
        while (index < string.length()) {
            tmpCluster = "";
            String newCluster = findCluster(string.toCharArray(), index);
            clusters.add(newCluster);
            index += newCluster.length();
        }
        return clusters.toArray(new String[0]);
    }

    private String findCluster(char[] chars, int index) {
        int i = index;
        if (i >= chars.length || (chars[i] != '(' && chars[i] != ')')) {
            throw new UnclusterizedStringException();
        }
        tmpCluster += chars[i];
        if (countChars(tmpCluster, '(') == countChars(tmpCluster, ')')) {
            return tmpCluster;
        }
        return findCluster(chars, ++i);
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

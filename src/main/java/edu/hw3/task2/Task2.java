package edu.hw3.task2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Task2 {

    public String[] clusterize(String string) {
        List<String> clusters = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') {
                stack.push(i);
            } else if (string.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    throw new UnclusterizedStringException();
                }
                int clusterStartIndex = stack.pop();
                if (stack.isEmpty()) {
                    clusters.add(string.substring(clusterStartIndex, i + 1));
                }
            } else {
                throw new UnclusterizedStringException();
            }
        }
        if (!stack.isEmpty()) {
            throw new UnclusterizedStringException();
        }
        return clusters.toArray(new String[0]);
    }
}

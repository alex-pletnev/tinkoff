package edu.hw5.task6;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Task6 {

    private static final Set<String> ESCAPED_CHARACTERS = new HashSet<>(Set.of(
        ".",
        "\\",
        "[",
        "]",
        "{",
        "}",
        "(",
        ")",
        "?",
        "*",
        "+",
        "|",
        "^",
        "$"
    ));

    public boolean isSubsequence(String subsequence, String sequence) {
        if (Objects.isNull(subsequence) || Objects.isNull(sequence)) {
            return false;
        }
        StringBuilder regex = new StringBuilder(".*");
        for (int i = 0; i < subsequence.length(); i++) {
            var currentChar = subsequence.charAt(i);
            if (ESCAPED_CHARACTERS.contains(String.valueOf(currentChar))) {
                throw new InvalidStringException();
            }
            regex.append(currentChar).append(".*");
        }
        return sequence.matches(regex.toString());
    }
}

package edu.project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Session {
    private final char[] answer;
    private final char[] currentAnswer;
    private final int maxAttempts;
    private final List<Character> mistakeList = new ArrayList<>();
    private int attempts = 0;

    public Session(int maxAttempts) throws InvalidWordException {
        this(Dictionary.randomWord().toCharArray(), maxAttempts);
    }

    public Session(char[] answer, int maxAttempts) throws InvalidWordException {
        this.answer = answer;
        if (answer.length == 0) {
            throw new InvalidWordException();
        }
        currentAnswer = new char[answer.length];
        this.maxAttempts = maxAttempts;
        Arrays.fill(currentAnswer, '*');
    }

    public boolean checkAnswer(char letter) {
        boolean isCorrect = false;
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == letter) {
                currentAnswer[i] = letter;
                isCorrect = true;
            }
        }
        if (!isCorrect && !mistakeList.contains(letter)) {
                attempts++;
                mistakeList.add(letter);
        }
        return isCorrect;
    }

    public boolean isLoose() {
        return attempts >= maxAttempts;
    }

    public boolean isWin() {
        for (char c : currentAnswer) {
            if (c == '*') {
                return false;
            }
        }
        return true;
    }

    public char[] getAnswer() {
        return answer;
    }

    public char[] getCurrentAnswer() {
        return currentAnswer;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getAttempts() {
        return attempts;
    }
}

package edu.hw1;

public class Task4 {
    String fixString(String string) {
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length - 1; i += 2) {
            char tmp;
            tmp = charArray[i];
            charArray[i] = charArray[i + 1];
            charArray[i + 1] = tmp;
        }
        return new String(charArray);
    }
}

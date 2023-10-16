package edu.hw1;

public class Task4 {
    String fixString(String string) {
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length
            && !(charArray.length % 2 == 1 && i + 1 == charArray.length); i += 2) {
            char tmp;
            tmp = charArray[i];
            charArray[i] = charArray[i + 1];
            charArray[i + 1] = tmp;
        }
        return new String(charArray);
    }
}

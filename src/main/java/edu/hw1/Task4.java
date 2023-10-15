package edu.hw1;

public class Task4 {
    String fixString(String string) {
        char[] charArray = string.toCharArray();
        char tmp;
        for (int i = 0; i < charArray.length; i += 2) {
            if (charArray.length % 2 == 1 && i + 1 == charArray.length) {
                break;
            }
            tmp = charArray[i];
            charArray[i] = charArray[i + 1];
            charArray[i + 1] = tmp;
        }
        return new String(charArray);
    }
}

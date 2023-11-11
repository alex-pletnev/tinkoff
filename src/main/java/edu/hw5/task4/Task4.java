package edu.hw5.task4;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {

    public boolean hasSpecialSymbols(String password) {
        if (Objects.isNull(password)) {
            return false;
        }
        String regex = "[~!@#$%^&*|]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}

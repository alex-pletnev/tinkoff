package edu.hw5.task7;

import java.util.Objects;

public class Task7 {

    public boolean hasMatchesWithRegex1(String string) {

        var regex = "^[01]{2}0[01]*$";
        return hasMatchesWithRegex(string, regex);
    }

    public boolean hasMatchesWithRegex2(String string) {
        var regex = "^(1[01]*1|0[01]*0)$";
        return hasMatchesWithRegex(string, regex);
    }

    public boolean hasMatchesWithRegex3(String string) {
        var regex = "^[01]{1,3}$";
        return hasMatchesWithRegex(string, regex);
    }

    private boolean hasMatchesWithRegex(String string, String regex) {
        if (Objects.isNull(string)) {
            return false;
        }
        return string.matches(regex);
    }
}

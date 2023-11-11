package edu.hw5.task8;

import java.util.Objects;

public class Task8 {

    public boolean hasMatchesWithRegex1(String string) {
        var regex = "^([01]{2})*[01]$";
        return hasMatchesWithRegex(string, regex);
    }

    public boolean hasMatchesWithRegex2(String string) {
        var regex = "^(0([01]{2})*|1([01]{2})*[01])$";
        return hasMatchesWithRegex(string, regex);
    }

    public boolean hasMatchesWithRegex3(String string) {
        var regex = "^((1*01*){3})*$";
        return hasMatchesWithRegex(string, regex);
    }

    public boolean hasMatchesWithRegex4(String string) {
        var regex = "^(?!11$|111$)[01]*";
        return hasMatchesWithRegex(string, regex);
    }

    public boolean hasMatchesWithRegex5(String string) {
        var regex = "^(1([01]|$))+$";
        return hasMatchesWithRegex(string, regex);
    }

    public boolean hasMatchesWithRegex6(String string) {
        var regex = "^((0+0+[10]?)|(0+[10]?0+)|([01]?0+0+))$";
        return hasMatchesWithRegex(string, regex);
    }

    public boolean hasMatchesWithRegex7(String string) {
        var regex = "^(0+|^)(1?(0+|$))+$";
        return hasMatchesWithRegex(string, regex);
    }

    private boolean hasMatchesWithRegex(String string, String regex) {
        if (Objects.isNull(string)) {
            return false;
        }
        return string.matches(regex);
    }
}

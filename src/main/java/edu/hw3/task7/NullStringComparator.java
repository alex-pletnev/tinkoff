package edu.hw3.task7;

import java.util.Comparator;
import java.util.Objects;

public class NullStringComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        if (Objects.isNull(o1) && Objects.isNull(o2)) {
            return 0;
        }
        if (Objects.isNull(o1)) {
            return -1;
        }
        if (Objects.isNull(o2)) {
            return 1;
        }
        return o1.compareTo(o2);
    }
}


package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser5 extends DateParser {

    @Override
    public Optional<LocalDate> stringToLocalDate(String string) {
        if (string.equals("tomorrow")) {
            LocalDate localDate = LocalDate.now();
            localDate = localDate.plusDays(1);
            return Optional.of(localDate);
        }
        return Optional.empty();
    }
}

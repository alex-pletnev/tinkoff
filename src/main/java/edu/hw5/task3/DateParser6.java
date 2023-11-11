package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser6 extends DateParser {

    @Override
    public Optional<LocalDate> stringToLocalDate(String string) {
        if (string.equals("today")) {
            return Optional.of(LocalDate.now());
        }
        return Optional.empty();
    }
}

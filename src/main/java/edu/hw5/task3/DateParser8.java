package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser8 extends DateParser {

    @Override
    public Optional<LocalDate> stringToLocalDate(String string) {
        String regex = "\\d+ days? ago";
        if (string.matches(regex)) {
            LocalDate localDate = LocalDate.now();
            int days = Integer.parseInt(string.split(" ")[0]);
            localDate = localDate.minusDays(days);
            return Optional.of(localDate);

        }
        return Optional.empty();
    }
}

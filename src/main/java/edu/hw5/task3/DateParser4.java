package edu.hw5.task3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

public class DateParser4 extends DateParser {

    @Override
    public Optional<LocalDate> stringToLocalDate(String string) {
        String regex = "\\d/\\d/\\d{2}";
        if (string.matches(regex)) {
            SimpleDateFormat format = new SimpleDateFormat("d/M/yy");
            try {
                LocalDate localDate = format.parse(string)
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
                return Optional.of(localDate);
            } catch (ParseException parseException) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}

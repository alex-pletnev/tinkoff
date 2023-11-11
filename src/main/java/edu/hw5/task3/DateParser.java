package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public abstract class DateParser {
    private DateParser nextDateParser;

    public void setNextDateParser(DateParser nextDateParser) {
        this.nextDateParser = nextDateParser;
    }

    public Optional<LocalDate> parseDate(String string) {
        var date = stringToLocalDate(string);
        if (date.isPresent()) {
            return date;
        }
        if (!Objects.isNull(nextDateParser)) {
            return nextDateParser.parseDate(string);
        }
        return Optional.empty();
    }

    public abstract Optional<LocalDate> stringToLocalDate(String string);
}

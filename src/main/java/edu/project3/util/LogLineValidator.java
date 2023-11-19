package edu.project3.util;

import edu.project3.Arguments;
import edu.project3.entity.LogLine;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class LogLineValidator {

    private LogLineValidator() {
    }

    public static boolean validateLine(LogLine logLine, Arguments arguments) {
        var fromStr = arguments.getFrom();
        var toStr = arguments.getTo();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        if (!Objects.isNull(fromStr)) {
            OffsetDateTime from = OffsetDateTime.parse(fromStr, dateTimeFormatter);
            return logLine.date().isAfter(from);
        }
        if (!Objects.isNull(toStr)) {
            OffsetDateTime to = OffsetDateTime.parse(toStr, dateTimeFormatter);
            return logLine.date().isBefore(to);
        }
        return true;
    }
}

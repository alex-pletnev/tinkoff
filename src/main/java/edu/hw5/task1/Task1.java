package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Task1 {

    public String getTotalTime(List<String> strings) {
        if (Objects.isNull(strings) || strings.isEmpty()) {
            return "0м";
        }
        Duration totalDuration = Duration.ZERO;
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendLiteral(", ")
            .append(DateTimeFormatter.ISO_LOCAL_TIME)
            .toFormatter();
        List<Duration> durations = strings.stream()
            .map(string -> string.split(" - "))
            .map(fromTo ->
                Arrays.stream(fromTo)
                    .map(t -> LocalDateTime.parse(t, dateTimeFormatter))
                    .toList())
            .map(fromTo -> Duration.between(fromTo.get(0), fromTo.get(1)))
            .toList();
        for (var duration : durations) {
            totalDuration = totalDuration.plus(duration);
        }
        var meanSecond = totalDuration.getSeconds() / strings.size();
        var meanDuration = Duration.ZERO.plusSeconds(meanSecond);

        return getDurationPrettyPrintString(meanDuration);

    }

    private String getDurationPrettyPrintString(Duration duration) {
        StringBuilder stringBuilder = new StringBuilder();
        if (duration.toDaysPart() != 0) {
            stringBuilder.append(duration.toDaysPart()).append("д ");
        }
        if (duration.toHoursPart() != 0) {
            stringBuilder.append(duration.toHoursPart()).append("ч ");
        }
        if (duration.toMinutesPart() != 0) {
            stringBuilder.append(duration.toMinutesPart()).append("м");
        }
        return stringBuilder.toString();

    }

}

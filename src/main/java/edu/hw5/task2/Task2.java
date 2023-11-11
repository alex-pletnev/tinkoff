package edu.hw5.task2;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task2 {
    private static final int GOAL_DATE = 13;

    public List<LocalDate> findBlackFridays(int year) {
        if (year <= 0) {
            throw new DateTimeException("Year must be bigger then 0");
        }
        ArrayList<LocalDate> blackFridays = new ArrayList<>();
        var date = LocalDate.of(year, 1, GOAL_DATE);
        while (date.getYear() == year) {
            if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                blackFridays.add(date);
            }
            date = date.plusMonths(1);
        }
        return blackFridays;
    }

    public LocalDate findClosestBlackFriday(LocalDate from) {
        if (Objects.isNull(from)) {
            throw new DateTimeException("'from' mustn't be null");
        }
        var nextFriday = from;
        while (nextFriday.getDayOfMonth() != GOAL_DATE) {
            nextFriday = nextFriday.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return nextFriday;
    }

}

package edu.hw5;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import edu.hw5.task2.Task2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task2Test {

    @Test
    void findBlackFridaysSimpleTest() {
        var year = 1925;
        Task2 task2 = new Task2();
        var actual = task2.findBlackFridays(year);
        List<LocalDate> excepted = new ArrayList<>();
        excepted.add(LocalDate.of(1925, 2, 13));
        excepted.add(LocalDate.of(1925, 3, 13));
        excepted.add(LocalDate.of(1925, 11, 13));
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void findBlackFridaysNegativeYearTest() {
        Task2 task2 = new Task2();
        Assertions.assertThrows(DateTimeException.class, () -> task2.findBlackFridays(-1925));
    }

    @Test
    void findClosestBlackFridaySimpleTest() {
        var from = LocalDate.of(1925, 1, 10);
        Task2 task2 = new Task2();
        var actual = task2.findClosestBlackFriday(from);
        var excepted = LocalDate.of(1925, 2, 13);
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void findClosestBlackFridayWithNullTest() {
        Task2 task2 = new Task2();
        Assertions.assertThrows(DateTimeException.class, () -> task2.findClosestBlackFriday(null));
    }
}

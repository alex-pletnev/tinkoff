package edu.hw5;

import edu.hw5.task1.Task1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class Task1Test {

    @Test
    void getTotalTimeSimpleTest() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("2022-03-12, 20:20 - 2022-03-12, 23:50");
        strings.add("2022-04-01, 21:30 - 2022-04-02, 01:20");
        Task1 task1 = new Task1();
        var actual = task1.getTotalTime(strings);
        var excepted = "3ч 40м";
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void getTotalTimeWithDaysTest() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("2022-03-12, 20:20 - 2023-03-12, 23:50");
        strings.add("2022-04-01, 21:30 - 2022-04-02, 01:20");
        strings.add("2022-04-01, 21:30 - 2022-05-02, 01:20");
        strings.add("2020-04-01, 21:30 - 2022-06-02, 01:20");
        strings.add("2022-04-01, 21:30 - 2023-07-02, 01:20");
        Task1 task1 = new Task1();
        var actual = task1.getTotalTime(strings);
        var excepted = "328д 13ч 22м";
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void getTotalTimeWithEmptyListTest() {
        ArrayList<String> strings = new ArrayList<>();
        Task1 task1 = new Task1();
        var actual = task1.getTotalTime(strings);
        var excepted = "0м";
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void getTotalTimeWithNullArgTest() {
        ArrayList<String> strings = null;
        Task1 task1 = new Task1();
        var actual = task1.getTotalTime(strings);
        var excepted = "0м";
        Assertions.assertEquals(excepted, actual);
    }
}

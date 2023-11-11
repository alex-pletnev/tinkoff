package edu.hw5;

import edu.hw5.task3.Task3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class Task3Test {

    private Task3 task3;

    @BeforeEach
    public void setUp() {
        task3 = new Task3();
    }

    @Test
    void dateParserFailedTest() {
        var string = "2020/10-10";
        var actual = task3.parseDate(string).orElse(null);
        Assertions.assertNull(actual);
    }

    @Test
    void dateParser1SuccessTest() {
        var string = "2020-10-10";
        var actual = task3.parseDate(string).orElse(null);
        var excepted = LocalDate.of(2020, 10, 10);
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void dateParser2SuccessTest() {
        var string = "2020-12-2";
        var actual = task3.parseDate(string).orElse(null);
        var excepted = LocalDate.of(2020, 12, 2);
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void dateParser3SuccessTest() {
        var string = "1/3/1976";
        var actual = task3.parseDate(string).orElse(null);
        var excepted = LocalDate.of(1976, 3, 1);
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void dateParser4SuccessTest() {
        var string = "1/3/20";
        var actual = task3.parseDate(string).orElse(null);
        var excepted = LocalDate.of(2020, 3, 1);
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void dateParser5SuccessTest() {
        var string = "tomorrow";
        var actual = task3.parseDate(string).orElse(null);
        var excepted = LocalDate.now().plusDays(1);
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void dateParser6SuccessTest() {
        var string = "today";
        var actual = task3.parseDate(string).orElse(null);
        var excepted = LocalDate.now();
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void dateParser7SuccessTest() {
        var string = "yesterday";
        var actual = task3.parseDate(string).orElse(null);
        var excepted = LocalDate.now().minusDays(1);
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void dateParser8DaySuccessTest() {
        var string = "1 day ago";
        var actual = task3.parseDate(string).orElse(null);
        var excepted = LocalDate.now().minusDays(1);
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    void dateParser8DaysSuccessTest() {
        var string = "2234 days ago";
        var actual = task3.parseDate(string).orElse(null);
        var excepted = LocalDate.now().minusDays(2234);
        Assertions.assertEquals(excepted, actual);
    }





}

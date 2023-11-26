package edu.hw7;

import edu.hw7.task1.Task1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task1Test {

    private Task1 task1;

    @BeforeEach
    public void setUp() {
        task1 = new Task1();
    }



    @ParameterizedTest
    @CsvSource({"1, 1", "100, 100", "10, 10", "0, 0", "1000, 1000"})
    void raceTest(int threadCount, int excepted) throws InterruptedException {
        var actual = task1.race(threadCount);Assertions.assertEquals(excepted, actual);
    }
}

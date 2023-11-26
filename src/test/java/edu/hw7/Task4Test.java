package edu.hw7;

import edu.hw7.task4.PiMultiThreadCounter;
import edu.hw7.task4.PiOneThreadCounter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Task4Test {

    private static final Logger LOGGER = LogManager.getLogger();
    private PiOneThreadCounter piOneThreadCounter;
    private PiMultiThreadCounter piMultiThreadCounter;

    @BeforeEach
    public void setUp() {
        piOneThreadCounter = new PiOneThreadCounter();
        piMultiThreadCounter = new PiMultiThreadCounter();
    }

    @ParameterizedTest
    @ValueSource(ints = {10_000_000, 100_000_000, 1_000_000_000})
    void piOneThreadCounterTest(int n) {
        Assertions.assertDoesNotThrow(
            () -> {
                var pi = Math.PI;
                var startTime = System.currentTimeMillis();
                var countedPi = piOneThreadCounter.piCount(n);
                var finishTime = System.currentTimeMillis();
                var executionTime = finishTime - startTime;
                LOGGER.info("SingleThread execution time with n = {}: {}ms", n, executionTime);
                LOGGER.info("Deviation from Math.PI: {}", Math.abs(pi - countedPi));
            }
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {10_000_000, 100_000_000, 1_000_000_000})
    void piMultiThreadCounterTest(int n) {
        Assertions.assertDoesNotThrow(
            () -> {
                var pi = Math.PI;
                var startTime = System.currentTimeMillis();
                var countedPi = piMultiThreadCounter.piCount(n);
                var finishTime = System.currentTimeMillis();
                var executionTime = finishTime - startTime;
                LOGGER.info("MultiThread execution time with n = {}: {}ms", n, executionTime);
                LOGGER.info("Deviation from Math.PI: {}", Math.abs(pi - countedPi));
            }
        );
    }

    /**
     * MultiThread is 6.55 times faster than OneThread at n: 10000000
     * MultiThread is 17.38425925925926 times faster than OneThread at n: 100000000
     * MultiThread is 22.60169491525424 times faster than OneThread at n: 1000000000
     */

    @ParameterizedTest
    @ValueSource(ints = {10_000_000, 100_000_000, 1_000_000_000})
    void oneVcMultiBattle(int n) {
        Assertions.assertDoesNotThrow(
            () -> {
                var pi = Math.PI;
                double startTime = System.currentTimeMillis();
                var oneCountedPi = piOneThreadCounter.piCount(n);
                double finishTime = System.currentTimeMillis();
                double oneExecutionTime = finishTime - startTime;
                startTime = System.currentTimeMillis();
                var multiCountedPi = piMultiThreadCounter.piCount(n);
                finishTime = System.currentTimeMillis();
                double multiExecutionTime = finishTime - startTime;
                LOGGER.info(
                    "MultiThread is {} times faster than OneThread at n: {}",
                    oneExecutionTime / multiExecutionTime,
                    n
                );
            }
        );
    }

}

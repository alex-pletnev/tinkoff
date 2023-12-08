package edu.hw9;

import edu.hw9.task1.MetricStats;
import edu.hw9.task1.StatsCollector;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class Task1Test {
    private static final Logger LOGGER = LogManager.getLogger();
    private StatsCollector statsCollector;

    @BeforeEach
    public void setUp() {
        statsCollector = new StatsCollector();
    }

    @AfterEach
    public void tearDown() {
        statsCollector.shutdown();
    }

    @Test
    void getCompletedStatsTest() throws InterruptedException {
        // Arrange
        String metricName = "testMetric";
        double[] values = {1.0, 2.0, 3.0};
        statsCollector.push(metricName, values);

        // Act
        List<MetricStats> statsList = statsCollector.getCompletedStats();

        // Assert
        Assertions.assertFalse(statsList.isEmpty(), "The stats list should not be empty.");
        MetricStats metricStats = statsList.get(0);
        Assertions.assertEquals(metricName, metricStats.metricName(), "Metric name should match.");
        Assertions.assertEquals(6.0, metricStats.sum(), "Sum should be calculated correctly.");
        Assertions.assertEquals(2.0, metricStats.average(), "Average should be calculated correctly.");
        Assertions.assertEquals(3.0, metricStats.max(), "Max should be calculated correctly.");
        Assertions.assertEquals(1.0, metricStats.min(), "Min should be calculated correctly.");
    }

    @RepeatedTest(value = 100)
    void pushInMultiThreadedEnvironmentTest() throws InterruptedException {
        // Arrange
        int numThreads = 10;
        int numValuesPerThread = 100;
        int expectedNumStats = numThreads;
        CountDownLatch latch = new CountDownLatch(numThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        // Act
        for (int i = 0; i < numThreads; i++) {
            int index = i;
            executorService.execute(() -> {
                String metricName = "testMetric" + index;
                double[] values = generateRandomValues(numValuesPerThread);
                statsCollector.push(metricName, values);
                latch.countDown();
            });
        }

        // Wait for all threads to complete
        latch.await();

        // Assert
        List<MetricStats> statsList = statsCollector.getCompletedStats();
        statsList.forEach((s) -> LOGGER.info(
            "name: '{}', sum: {}, average: {}, max: {}, min: {}, size: {}",
            s.metricName(),
            s.sum(),
            s.average(),
            s.max(),
            s.min(),
            s.size()
        ));

        Assertions.assertEquals(
            expectedNumStats,
            statsList.size(),
            "The number of completed stats should match the number of threads."
        );

        for (MetricStats metricStats : statsList) {
            Assertions.assertEquals(
                numValuesPerThread,
                metricStats.size(),
                "The number of values in each MetricStats should match the number of values per thread."
            );
        }
    }

    private synchronized double[] generateRandomValues(int numValues) {
        double[] values = new double[numValues];
        for (int i = 0; i < numValues; i++) {
            values[i] = Math.random();
        }
        return values;
    }
}

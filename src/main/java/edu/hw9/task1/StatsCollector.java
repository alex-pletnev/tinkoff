package edu.hw9.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatsCollector {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Map<String, List<Double>> data;
    private final ExecutorService executor;

    public StatsCollector() {
        this.data = new ConcurrentHashMap<>();
        this.executor = Executors.newFixedThreadPool(5);
    }

    public void push(String metricName, double[] values) {
        data.computeIfAbsent(metricName, k -> new ArrayList<>());

        for (double value : values) {
            data.get(metricName).add(value);
        }
    }

    private List<Future<MetricStats>> calculateStatsAsync() {
        List<Future<MetricStats>> futures = new ArrayList<>();

        for (Map.Entry<String, List<Double>> entry : data.entrySet()) {
            String metricName = entry.getKey();
            List<Double> values = entry.getValue();

            Callable<MetricStats> statsTask = () -> {
                double sum = 0;
                int size = 0;
                double max = Double.MIN_VALUE;
                double min = Double.MAX_VALUE;

                for (double value : values) {
                    size++;
                    sum += value;
                    max = Math.max(max, value);
                    min = Math.min(min, value);
                }

                double average = sum / values.size();

                return new MetricStats(metricName, sum, average, max, min, size);
            };

            Future<MetricStats> future = executor.submit(statsTask);
            futures.add(future);
        }

        return futures;
    }

    public List<MetricStats> getCompletedStats() throws InterruptedException {
        List<Future<MetricStats>> futures = calculateStatsAsync();
        List<MetricStats> result = new ArrayList<>();

        for (Future<MetricStats> future : futures) {
            try {
                MetricStats metricStats = future.get();
                result.add(metricStats);
            } catch (ExecutionException e) {
                LOGGER.error(e);
            }
        }

        return result;
    }

    public void shutdown() {
        executor.shutdown();
    }
}

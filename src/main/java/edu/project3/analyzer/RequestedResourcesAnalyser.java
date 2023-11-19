package edu.project3.analyzer;

import edu.project3.entity.LogLine;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestedResourcesAnalyser {

    private final Map<String, Integer> statsMap;

    public RequestedResourcesAnalyser(List<LogLine> logLineList) {
        statsMap = new HashMap<>();
        logLineList.forEach(
            line -> statsMap.merge(line.resource(), 1, Integer::sum)
        );

    }

    public List<Map.Entry<String, Integer>> getMostPopularResources() {
        var list = new ArrayList<Map.Entry<String, Integer>>();
        statsMap.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .forEach(
                e -> list.add(new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue()))
            );

        return list;
    }
}

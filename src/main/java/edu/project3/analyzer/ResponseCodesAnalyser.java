package edu.project3.analyzer;

import edu.project3.entity.LogLine;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseCodesAnalyser {
    private final Map<Integer, Integer> statsMap;

    public static final Map<Integer, String> RESPONSE_CODES_DESCRIPTION = new HashMap<>();

    static {
        RESPONSE_CODES_DESCRIPTION.put(200, "OK");
        RESPONSE_CODES_DESCRIPTION.put(206, "Partial Content");
        RESPONSE_CODES_DESCRIPTION.put(304, "Not Modified");
        RESPONSE_CODES_DESCRIPTION.put(403, "Forbidden");
        RESPONSE_CODES_DESCRIPTION.put(404, "Not Found");
        RESPONSE_CODES_DESCRIPTION.put(416, "Range Not Satisfiable");

    }

    public ResponseCodesAnalyser(List<LogLine> logLineList) {
        statsMap = new HashMap<>();
        logLineList.forEach(
            line -> statsMap.merge(line.responseCode(), 1, Integer::sum)
        );

    }

    public List<Map.Entry<Integer, Integer>> getMostPopularResponseCodes() {
        var list = new ArrayList<Map.Entry<Integer, Integer>>();
        statsMap.entrySet().stream()
            .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
            .forEach(
                e -> list.add(new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue()))
            );

        return list;
    }
}

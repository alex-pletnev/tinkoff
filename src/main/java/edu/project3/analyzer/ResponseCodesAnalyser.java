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

    private static final int OK = 200;
    private static final int PC = 206;
    private static final int NM = 304;
    private static final int FB = 403;
    private static final int NF = 404;
    private static final int RNS = 416;

    static {
        RESPONSE_CODES_DESCRIPTION.put(OK, "OK");
        RESPONSE_CODES_DESCRIPTION.put(PC, "Partial Content");
        RESPONSE_CODES_DESCRIPTION.put(NM, "Not Modified");
        RESPONSE_CODES_DESCRIPTION.put(FB, "Forbidden");
        RESPONSE_CODES_DESCRIPTION.put(NF, "Not Found");
        RESPONSE_CODES_DESCRIPTION.put(RNS, "Range Not Satisfiable");

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

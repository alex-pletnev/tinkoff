package edu.project3;

import edu.project3.analyzer.ResponseCodesAnalyser;
import edu.project3.entity.LogLine;
import edu.project3.entity.RequestType;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResponseCodesAnalyserTest {
    private ResponseCodesAnalyser responseCodesAnalyser;

    @BeforeEach
    public void setUp() {
        List<LogLine> logLineList = new ArrayList<>();
        responseCodesAnalyser = new ResponseCodesAnalyser(logLineList);
    }

    @Test
    void testGetMostPopularResponseCodesEmptyList() {
        List<Map.Entry<Integer, Integer>> result = responseCodesAnalyser.getMostPopularResponseCodes();
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetMostPopularResponseCodesReturnSortedList() {
        List<LogLine> logLineList = new ArrayList<>();
        logLineList.add(new LogLine("127.0.0.1", OffsetDateTime.now(), RequestType.GET, "/index.html", 200, 512));
        logLineList.add(new LogLine("127.0.0.1", OffsetDateTime.now(), RequestType.GET, "/index.html", 404, 512));
        logLineList.add(new LogLine("127.0.0.1", OffsetDateTime.now(), RequestType.GET, "/index.html", 200, 512));
        responseCodesAnalyser = new ResponseCodesAnalyser(logLineList);

        List<Map.Entry<Integer, Integer>> result = responseCodesAnalyser.getMostPopularResponseCodes();
        assertEquals(2, result.size());
        assertEquals(200, result.get(0).getKey());
        assertEquals(2, result.get(0).getValue());
        assertEquals(404, result.get(1).getKey());
        assertEquals(1, result.get(1).getValue());
    }

    @Test
    void testGetMostPopularResponseCodesWithSingleEntry() {
        List<LogLine> logLineList = new ArrayList<>();
        logLineList.add(new LogLine("127.0.0.1", OffsetDateTime.now(), RequestType.GET, "/index.html", 200, 512));
        logLineList.add(new LogLine("127.0.0.1", OffsetDateTime.now(), RequestType.GET, "/index.html", 200, 512));
        responseCodesAnalyser = new ResponseCodesAnalyser(logLineList);

        List<Map.Entry<Integer, Integer>> result = responseCodesAnalyser.getMostPopularResponseCodes();
        assertEquals(1, result.size());
        assertEquals(200, result.get(0).getKey());
        assertEquals(2, result.get(0).getValue());
    }
}

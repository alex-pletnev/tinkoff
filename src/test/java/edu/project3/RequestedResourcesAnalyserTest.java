package edu.project3;

import edu.project3.analyzer.RequestedResourcesAnalyser;
import edu.project3.entity.LogLine;
import edu.project3.entity.RequestType;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RequestedResourcesAnalyserTest {

    private RequestedResourcesAnalyser requestedResourcesAnalyser;
    private List<LogLine> logLineList;

    @BeforeEach
    public void setUp() {
        LogLine logLine1 = new LogLine(
            "127.0.0.1",
            OffsetDateTime.of(2022, 1, 1, 0, 0, 0, 0, OffsetDateTime.now().getOffset()),
            RequestType.GET,
            "/index.html",
            200,
            500
        );
        LogLine logLine2 = new LogLine(
            "127.0.0.1",
            OffsetDateTime.of(2022, 1, 2, 0, 0, 0, 0, OffsetDateTime.now().getOffset()),
            RequestType.POST,
            "/login.html",
            200,
            1000
        );
        LogLine logLine3 = new LogLine(
            "127.0.0.1",
            OffsetDateTime.of(2022, 1, 3, 0, 0, 0, 0, OffsetDateTime.now().getOffset()),
            RequestType.GET,
            "/index.html",
            200,
            1500
        );
        logLineList = Arrays.asList(logLine1, logLine2, logLine3);
        requestedResourcesAnalyser = new RequestedResourcesAnalyser(logLineList);
    }

    @Test
    void testGetMostPopularResources() {
        List<Map.Entry<String, Integer>> expectedResources = Arrays.asList(
            Map.entry("/index.html", 2),
            Map.entry("/login.html", 1)
        );
        List<Map.Entry<String, Integer>> actualResources = requestedResourcesAnalyser.getMostPopularResources();
        Assertions.assertEquals(expectedResources, actualResources);
    }
}

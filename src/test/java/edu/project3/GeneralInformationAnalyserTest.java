package edu.project3;

import edu.project3.analyzer.GeneralInformationAnalyser;
import edu.project3.entity.LogLine;
import edu.project3.entity.RequestType;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GeneralInformationAnalyserTest {

    private GeneralInformationAnalyser generalInformationAnalyser;
    private List<LogLine> logLineList;
    private String source;

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
        logLineList = Arrays.asList(logLine1, logLine2);
        source = "testSource";
        generalInformationAnalyser = new GeneralInformationAnalyser(logLineList, source);
    }

    @Test
    @DisplayName("Test getSource method when source is set")
    void testGetSource() {
        String actualSource = generalInformationAnalyser.getSource();
        Assertions.assertEquals(source, actualSource);
    }

    @Test
    @DisplayName("Test getStartDate method when logLineList is not empty")
    void testGetStartDate() {
        String expectedStartDate = "1.1.2022";
        String actualStartDate = generalInformationAnalyser.getStartDate();
        Assertions.assertEquals(expectedStartDate, actualStartDate);
    }

    @Test
    @DisplayName("Test getFinishDate method when logLineList is not empty")
    void testGetFinishDate() {
        String expectedFinishDate = "2.1.2022";
        String actualFinishDate = generalInformationAnalyser.getFinishDate();
        Assertions.assertEquals(expectedFinishDate, actualFinishDate);
    }

    @Test
    @DisplayName("Test getRequestCount method when logLineList is not empty")
    void testGetRequestCount() {
        int expectedRequestCount = 2;
        int actualRequestCount = generalInformationAnalyser.getRequestCount();
        Assertions.assertEquals(expectedRequestCount, actualRequestCount);
    }

    @Test
    @DisplayName("Test averageResponseSize method when logLineList is not empty")
    void testAverageResponseSize() {
        long expectedAverageResponseSize = 750;
        long actualAverageResponseSize = generalInformationAnalyser.averageResponseSize();
        Assertions.assertEquals(expectedAverageResponseSize, actualAverageResponseSize);
    }
}

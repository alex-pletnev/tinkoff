package edu.hw2;

import edu.hw2.task1.Task1;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class Task1Test {
    private Task1 task1;
    private Logger logger;

    @BeforeEach
    public void setup() {
        logger = Mockito.mock(Logger.class);
        task1 = new Task1(logger);
    }

    @Test
    @DisplayName("Test calculate")
    void calculateTest() {
        String expectedLogMessage = "((((2.0+4.0)*-(1.0))^2)+1.0) = 37.0";
        task1.calculate();
        verify(logger, times(1)).info(expectedLogMessage);
    }
}

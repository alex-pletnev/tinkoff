package edu.hw1;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class Task0Test {
    private Task0 task0;
    private Logger logger;

    @BeforeEach
    void setUp() {
        logger = Mockito.mock(Logger.class);
        task0 = new Task0(logger);
    }

    @Test
void testRunWhenCalledThenLogsMessage() {
        // Подготовка
        String expectedMessage = "Привет, мир!";

        // Действие
        task0.run();

        // Проверка
        verify(logger, times(1)).info(expectedMessage);
    }
}

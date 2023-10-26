package edu.project1;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleHangmanTest {
    @Test
    void testInvalidWordException() {
        assertThrows(InvalidWordException.class, () -> {
            new Session(new char[] {}, 1);
        });
    }

    @Test
    void testRun_ExpectFalse() throws FileNotFoundException {
        Logger logger = Mockito.mock(Logger.class);
        Session session = new Session(5);
        Reader reader = new Reader("src/main/resources/pr1data1");

        ConsoleHangman consoleHangman = new ConsoleHangman(logger, session, reader);
        boolean result = consoleHangman.run();

        assertFalse(result);
    }

    @Test
    void testRun_ExpectTrue() throws FileNotFoundException {
        Logger logger = Mockito.mock(Logger.class);
        Session session = new Session(new char[] {'h', 'e', 'l', 'l', 'o'}, 5);
        Reader reader = new Reader("src/main/resources/pr1data2");

        ConsoleHangman consoleHangman = new ConsoleHangman(logger, session, reader);
        boolean result = consoleHangman.run();

        assertTrue(result);
    }


}

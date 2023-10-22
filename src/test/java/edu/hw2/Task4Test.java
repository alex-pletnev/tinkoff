package edu.hw2;

import edu.hw2.task4.CallingInfoManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class Task4Test {
    @Test
    void callingInfoTest1() {
        // Arrange
        // No arrangement needed as we are testing a static method

        // Act
        CallingInfoManager.CallingInfo callingInfo = CallingInfoManager.callingInfo();

        // Assert
        assertNotNull(callingInfo);
        assertEquals("edu.hw2.Task4Test", callingInfo.className());
        assertEquals("callingInfoTest1", callingInfo.methodName());
    }
    @Test
    void callingInfoTest2() {
        // Arrange
        // No arrangement needed as we are testing a static method

        // Act
        CallingInfoManager.CallingInfo callingInfo = CallingInfoManager.callingInfo();

        // Assert
        assertNotNull(callingInfo);
        assertEquals("edu.hw2.Task4Test", callingInfo.className());
        assertEquals("callingInfoTest2", callingInfo.methodName());
    }
}

package edu.hw6.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PortScannerTest {

    @Test
    void scanTcpPortsTest() {
        Assertions.assertDoesNotThrow(PortScanner::printPortsInfo);
    }
}

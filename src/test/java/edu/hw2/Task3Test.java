package edu.hw2;

import edu.hw2.task3.Connection;
import edu.hw2.task3.FaultyConnection;
import edu.hw2.task3.FaultyConnectionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {
    private FaultyConnectionManager faultyConnectionManager;

    @BeforeEach
    public void setUp() {
        faultyConnectionManager = new FaultyConnectionManager();
    }

    @Test
    void testGetConnectionWhenCalledThenReturnsFaultyConnection() {
        Connection result = faultyConnectionManager.getConnection();
        assertThat(result).isInstanceOf(FaultyConnection.class);
    }
}

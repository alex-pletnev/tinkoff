package edu.hw9;

import edu.hw9.task3.Graph;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task3Test {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void testParallelDepthFirstSearch() {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        Assertions.assertDoesNotThrow(() -> {
                var start = System.currentTimeMillis();
                graph.parallelDepthFirstSearch(0);
                var finish = System.currentTimeMillis();
                var time = finish - start;
                LOGGER.info("parallelDepthFirstSearch finished after {} ms", time);

            }

        );

    }

}

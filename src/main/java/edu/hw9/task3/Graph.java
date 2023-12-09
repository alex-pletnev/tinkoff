package edu.hw9.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Graph {
    private static final Logger LOGGER = LogManager.getLogger();

    private final List<List<Integer>> adjacencyList;

    public Graph(int vertices) {
        this.adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            this.adjacencyList.add(new ArrayList<>());
        }
    }

    public synchronized void addEdge(int u, int v) {
        adjacencyList.get(u).add(v);
    }

    public synchronized List<Integer> getAdjacentVertices(int vertex) {
        return adjacencyList.get(vertex);
    }

    public void parallelDepthFirstSearch(int startVertex) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
            .availableProcessors())) {

            boolean[] visited = new boolean[adjacencyList.size()];

            Deque<Integer> stack = new ArrayDeque<>();
            stack.addFirst(startVertex);

            while (!stack.isEmpty()) {
                int currentVertex = stack.removeFirst();

                if (!visited[currentVertex]) {
                    visited[currentVertex] = true;
                    LOGGER.info("Visited vertex: {}", currentVertex);

                    List<Integer> adjacentVertices = getAdjacentVertices(currentVertex);
                    for (int adjacentVertex : adjacentVertices) {
                        if (!visited[adjacentVertex]) {
                            stack.addFirst(adjacentVertex);
                        }
                    }
                }
            }

            executorService.shutdown();
        }
    }
}

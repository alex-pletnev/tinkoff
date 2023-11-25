package edu.project2;

import edu.project2.exceptions.CoordinatesException;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MazeGenerator {

    private static final int BOTTOM_BIT = 0b0001;
    private static final int LEFT_BIT = 0b0010;
    private static final int TOP_BIT = 0b0100;
    private static final int RIGHT_BIT = 0b1000;
    private final Random random = new Random();
    private final Maze maze;
    private final boolean[][] visited;
    private final Deque<Map.Entry<Integer, Integer>> stack;

    public MazeGenerator(Maze maze) {
        this.maze = maze;
        this.visited = new boolean[maze.getRows()][maze.getCols()];
        this.stack = new ArrayDeque<>();
    }

    private void initMaze() {
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getCols(); j++) {
                maze.setCell(i, j, BOTTOM_BIT | LEFT_BIT | TOP_BIT | RIGHT_BIT);
            }
        }
    }

    public void generateMaze() {
        initMaze();
        Map.Entry<Integer, Integer> initialCell = new AbstractMap.SimpleEntry<>(0, 0);
        markAsVisited(initialCell);
        stack.push(initialCell);

        while (!stack.isEmpty()) {
            var currentCell = stack.pop();
            var unvisitedNeighbours = getUnvisitedNeighbours(currentCell);

            if (!unvisitedNeighbours.isEmpty()) {
                stack.push(currentCell);
                Map.Entry<Integer, Integer> chosenCell = chooseRandomNeighbour(unvisitedNeighbours);
                removeWall(currentCell, chosenCell);
                markAsVisited(chosenCell);
                stack.push(chosenCell);
            }
        }
    }

    private void markAsVisited(Map.Entry<Integer, Integer> cell) {
        visited[cell.getKey()][cell.getValue()] = true;
    }

    private List<Map.Entry<Integer, Integer>> getUnvisitedNeighbours(Map.Entry<Integer, Integer> cell) {
        List<Map.Entry<Integer, Integer>> neighbours = new ArrayList<>();

        if (cell.getKey() > 0 && !visited[cell.getKey() - 1][cell.getValue()]) {
            neighbours.add(new AbstractMap.SimpleEntry<>(cell.getKey() - 1, cell.getValue()));
        }
        if (cell.getKey() < maze.getRows() - 1 && !visited[cell.getKey() + 1][cell.getValue()]) {
            neighbours.add(new AbstractMap.SimpleEntry<>(cell.getKey() + 1, cell.getValue()));
        }
        if (cell.getValue() > 0 && !visited[cell.getKey()][cell.getValue() - 1]) {
            neighbours.add(new AbstractMap.SimpleEntry<>(cell.getKey(), cell.getValue() - 1));
        }
        if (cell.getValue() < maze.getCols() - 1 && !visited[cell.getKey()][cell.getValue() + 1]) {
            neighbours.add(new AbstractMap.SimpleEntry<>(cell.getKey(), cell.getValue() + 1));
        }

        return neighbours;
    }

    private Map.Entry<Integer, Integer> checkBottomNeighbour()

    private Map.Entry<Integer, Integer> chooseRandomNeighbour(List<Map.Entry<Integer, Integer>> neighbours) {
        int index = random.nextInt(neighbours.size());
        return neighbours.get(index);
    }

    private void removeWall(Map.Entry<Integer, Integer> cell1, Map.Entry<Integer, Integer> cell2) {
        int rowDiff = cell2.getKey() - cell1.getKey();
        int colDiff = cell2.getValue() - cell1.getValue();
        //bottom left top right

        if (rowDiff == -1) {
            maze.invertBitInCell(cell1.getKey(), cell1.getValue(), TOP_BIT);
            maze.invertBitInCell(cell2.getKey(), cell2.getValue(), BOTTOM_BIT);
        } else if (rowDiff == 1) {
            maze.invertBitInCell(cell1.getKey(), cell1.getValue(), BOTTOM_BIT);
            maze.invertBitInCell(cell2.getKey(), cell2.getValue(), TOP_BIT);
        } else if (colDiff == -1) {
            maze.invertBitInCell(cell1.getKey(), cell1.getValue(), LEFT_BIT);
            maze.invertBitInCell(cell2.getKey(), cell2.getValue(), RIGHT_BIT);
        } else if (colDiff == 1) {
            maze.invertBitInCell(cell1.getKey(), cell1.getValue(), RIGHT_BIT);
            maze.invertBitInCell(cell2.getKey(), cell2.getValue(), LEFT_BIT);
        }
    }
}

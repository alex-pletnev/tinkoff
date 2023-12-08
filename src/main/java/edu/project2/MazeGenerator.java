package edu.project2;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class MazeGenerator {

    private static final int BOTTOM_BIT = 0b0001;
    private static final int LEFT_BIT = 0b0010;
    private static final int TOP_BIT = 0b0100;
    private static final int RIGHT_BIT = 0b1000;
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
            var unvisitedNeighbours = MazeUtil.getUnvisitedNeighbours(currentCell, maze, visited, false);

            if (!unvisitedNeighbours.isEmpty()) {
                stack.push(currentCell);
                Map.Entry<Integer, Integer> chosenCell = MazeUtil.chooseRandomNeighbour(unvisitedNeighbours);
                removeWall(currentCell, chosenCell);
                markAsVisited(chosenCell);
                stack.push(chosenCell);
            }
        }
    }

    private void markAsVisited(Map.Entry<Integer, Integer> cell) {
        visited[cell.getKey()][cell.getValue()] = true;
    }

    private void removeWall(Map.Entry<Integer, Integer> cell1, Map.Entry<Integer, Integer> cell2) {
        int rowDiff = cell2.getKey() - cell1.getKey();
        int colDiff = cell2.getValue() - cell1.getValue();

        if (rowDiff == -1) {
            removeTopWall(cell1, cell2);
        } else if (rowDiff == 1) {
            removeBottomWall(cell1, cell2);
        } else if (colDiff == -1) {
            removeLeftWall(cell1, cell2);
        } else if (colDiff == 1) {
            removeRightWall(cell1, cell2);
        }
    }

    private void removeTopWall(Map.Entry<Integer, Integer> cell1, Map.Entry<Integer, Integer> cell2) {
        maze.invertBitInCell(cell1.getKey(), cell1.getValue(), TOP_BIT);
        maze.invertBitInCell(cell2.getKey(), cell2.getValue(), BOTTOM_BIT);
    }

    private void removeBottomWall(Map.Entry<Integer, Integer> cell1, Map.Entry<Integer, Integer> cell2) {
        maze.invertBitInCell(cell1.getKey(), cell1.getValue(), BOTTOM_BIT);
        maze.invertBitInCell(cell2.getKey(), cell2.getValue(), TOP_BIT);
    }

    private void removeLeftWall(Map.Entry<Integer, Integer> cell1, Map.Entry<Integer, Integer> cell2) {
        maze.invertBitInCell(cell1.getKey(), cell1.getValue(), LEFT_BIT);
        maze.invertBitInCell(cell2.getKey(), cell2.getValue(), RIGHT_BIT);
    }

    private void removeRightWall(Map.Entry<Integer, Integer> cell1, Map.Entry<Integer, Integer> cell2) {
        maze.invertBitInCell(cell1.getKey(), cell1.getValue(), RIGHT_BIT);
        maze.invertBitInCell(cell2.getKey(), cell2.getValue(), LEFT_BIT);
    }
}

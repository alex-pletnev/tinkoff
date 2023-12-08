package edu.project2;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class MazeUtil {
    private static final int BOTTOM_BIT = 0b0001;
    private static final int LEFT_BIT = 0b0010;
    private static final int TOP_BIT = 0b0100;
    private static final int RIGHT_BIT = 0b1000;

    private static final Random RANDOM = new Random();

    private MazeUtil() {
    }

    public static Map.Entry<Integer, Integer> chooseRandomNeighbour(List<Map.Entry<Integer, Integer>> neighbours) {
        int index = RANDOM.nextInt(neighbours.size());
        return neighbours.get(index);
    }

    public static List<Map.Entry<Integer, Integer>> getUnvisitedNeighbours(
        Map.Entry<Integer, Integer> cell,
        Maze maze,
        boolean[][] visited,
        boolean withWall
    ) {
        List<Map.Entry<Integer, Integer>> neighbours = new ArrayList<>();
        List<Map.Entry<Integer, Integer>> tmpNeighbours = new ArrayList<>();
        if (withWall) {
            tmpNeighbours.add(checkTopNeighbourWithWall(cell, maze, visited));
            tmpNeighbours.add(checkBottomNeighbourWithWall(cell, maze, visited));
            tmpNeighbours.add(checkLeftNeighbourWithWall(cell, maze, visited));
            tmpNeighbours.add(checkRightNeighbourWithWall(cell, maze, visited));
        } else {
            tmpNeighbours.add(checkTopNeighbour(cell, maze, visited));
            tmpNeighbours.add(checkBottomNeighbour(cell, maze, visited));
            tmpNeighbours.add(checkLeftNeighbour(cell, maze, visited));
            tmpNeighbours.add(checkRightNeighbour(cell, maze, visited));
        }

        for (var tmpNeighbour : tmpNeighbours) {
            if (!Objects.isNull(tmpNeighbour)) {
                neighbours.add(tmpNeighbour);
            }
        }

        return neighbours;
    }

    private static Map.Entry<Integer, Integer> checkTopNeighbour(
        Map.Entry<Integer, Integer> cell,
        Maze maze,
        boolean[][] visited
    ) {
        if (cell.getKey() > 0 && !visited[cell.getKey() - 1][cell.getValue()]) {
            return new AbstractMap.SimpleEntry<>(cell.getKey() - 1, cell.getValue());
        }
        return null;
    }

    private static Map.Entry<Integer, Integer> checkBottomNeighbour(
        Map.Entry<Integer, Integer> cell,
        Maze maze,
        boolean[][] visited
    ) {
        if (cell.getKey() < maze.getRows() - 1 && !visited[cell.getKey() + 1][cell.getValue()]) {
            return new AbstractMap.SimpleEntry<>(cell.getKey() + 1, cell.getValue());
        }
        return null;
    }

    private static Map.Entry<Integer, Integer> checkLeftNeighbour(
        Map.Entry<Integer, Integer> cell,
        Maze maze,
        boolean[][] visited
    ) {
        if (cell.getValue() > 0 && !visited[cell.getKey()][cell.getValue() - 1]) {
            return new AbstractMap.SimpleEntry<>(cell.getKey(), cell.getValue() - 1);
        }
        return null;
    }

    private static Map.Entry<Integer, Integer> checkRightNeighbour(
        Map.Entry<Integer, Integer> cell,
        Maze maze,
        boolean[][] visited
    ) {
        if (cell.getValue() < maze.getCols() - 1 && !visited[cell.getKey()][cell.getValue() + 1]) {
            return new AbstractMap.SimpleEntry<>(cell.getKey(), cell.getValue() + 1);
        }
        return null;
    }

    private static Map.Entry<Integer, Integer> checkTopNeighbourWithWall(
        Map.Entry<Integer, Integer> cell,
        Maze maze,
        boolean[][] visited
    ) {
        if (cell.getKey() > 0 && !visited[cell.getKey() - 1][cell.getValue()]
            && (maze.getCell(cell.getKey(), cell.getValue()) & TOP_BIT) == 0) {
            return new AbstractMap.SimpleEntry<>(cell.getKey() - 1, cell.getValue());
        }
        return null;
    }

    private static Map.Entry<Integer, Integer> checkBottomNeighbourWithWall(
        Map.Entry<Integer, Integer> cell,
        Maze maze,
        boolean[][] visited
    ) {
        if (cell.getKey() < maze.getRows() - 1 && !visited[cell.getKey() + 1][cell.getValue()]
            && (maze.getCell(cell.getKey(), cell.getValue()) & BOTTOM_BIT) == 0) {
            return new AbstractMap.SimpleEntry<>(cell.getKey() + 1, cell.getValue());
        }
        return null;
    }

    private static Map.Entry<Integer, Integer> checkLeftNeighbourWithWall(
        Map.Entry<Integer, Integer> cell,
        Maze maze,
        boolean[][] visited
    ) {
        if (cell.getValue() > 0 && !visited[cell.getKey()][cell.getValue() - 1]
            && (maze.getCell(cell.getKey(), cell.getValue()) & LEFT_BIT) == 0) {
            return new AbstractMap.SimpleEntry<>(cell.getKey(), cell.getValue() - 1);
        }
        return null;
    }

    private static Map.Entry<Integer, Integer> checkRightNeighbourWithWall(
        Map.Entry<Integer, Integer> cell,
        Maze maze,
        boolean[][] visited
    ) {
        if (cell.getValue() < maze.getCols() - 1 && !visited[cell.getKey()][cell.getValue() + 1]
            && (maze.getCell(cell.getKey(), cell.getValue()) & RIGHT_BIT) == 0) {
            return new AbstractMap.SimpleEntry<>(cell.getKey(), cell.getValue() + 1);
        }
        return null;
    }
}

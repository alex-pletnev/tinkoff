package edu.project2;

import edu.project2.exceptions.CoordinatesException;
import edu.project2.exceptions.RouteException;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MazeNavigator {
    private static final int BOTTOM_BIT = 1;
    private static final int LEFT_BIT = 2;
    private static final int TOP_BIT = 4;
    private static final int RIGHT_BIT = 8;
    private static final int X = 0;
    private static final int Y = 1;
    private static final String NO_ROUTE_FOUND = "No route found!";
    private final Random random = new Random();
    private final Maze maze;
    private final boolean[][] visited;
    private final Deque<Map.Entry<Integer, Integer>> stack;
    private final List<Map.Entry<Integer, Integer>> route;

    public MazeNavigator(Maze maze) {
        this.maze = maze;
        this.visited = new boolean[maze.getRows()][maze.getCols()];
        this.stack = new ArrayDeque<>();
        this.route = new ArrayList<>();
    }

    public int[][] navigateMaze(int x1, int y1, int x2, int y2) {
        if (x1 < 0 || x1 >= maze.getRows() || y1 < 0 || y1 >= maze.getCols()
            || x2 < 0 || x2 >= maze.getRows() || y2 < 0 || y2 >= maze.getCols()) {
            throw new CoordinatesException("Coordinates must be positive!");
        }

        Map.Entry<Integer, Integer> initialCell = new AbstractMap.SimpleEntry<>(x1, y1);
        markAsVisited(initialCell);
        stack.push(initialCell);

        while (!stack.isEmpty()) {
            Map.Entry<Integer, Integer> currentCell = stack.pop();

            if (currentCell.getKey() == x2 && currentCell.getValue() == y2) {
                route.add(currentCell);
                return convertRouteToArray();
            }

            List<Map.Entry<Integer, Integer>> unvisitedNeighbours = getUnvisitedNeighbours(currentCell);

            if (!unvisitedNeighbours.isEmpty()) {
                route.add(currentCell);
                stack.push(currentCell);
                Map.Entry<Integer, Integer> chosenCell = chooseRandomNeighbour(unvisitedNeighbours);
                markAsVisited(chosenCell);
                stack.push(chosenCell);
            } else {
                if (route.isEmpty()) {
                    throw new RouteException(NO_ROUTE_FOUND);
                }
                route.remove(route.size() - 1);
            }
        }

        throw new RouteException(NO_ROUTE_FOUND);
    }

    private void markAsVisited(Map.Entry<Integer, Integer> cell) {
        visited[cell.getKey()][cell.getValue()] = true;
    }

    private List<Map.Entry<Integer, Integer>> getUnvisitedNeighbours(Map.Entry<Integer, Integer> cell) {
        List<Map.Entry<Integer, Integer>> neighbours = new ArrayList<>();

        if (cell.getKey() > 0 && !visited[cell.getKey() - 1][cell.getValue()]
            && (maze.getCell(cell.getKey(), cell.getValue()) & TOP_BIT) == 0) {
            neighbours.add(new AbstractMap.SimpleEntry<>(cell.getKey() - 1, cell.getValue()));
        }
        if (cell.getKey() < maze.getRows() - 1 && !visited[cell.getKey() + 1][cell.getValue()]
            && (maze.getCell(cell.getKey(), cell.getValue()) & BOTTOM_BIT) == 0) {
            neighbours.add(new AbstractMap.SimpleEntry<>(cell.getKey() + 1, cell.getValue()));
        }
        if (cell.getValue() > 0 && !visited[cell.getKey()][cell.getValue() - 1]
            && (maze.getCell(cell.getKey(), cell.getValue()) & LEFT_BIT) == 0) {
            neighbours.add(new AbstractMap.SimpleEntry<>(cell.getKey(), cell.getValue() - 1));
        }
        if (cell.getValue() < maze.getCols() - 1 && !visited[cell.getKey()][cell.getValue() + 1]
            && (maze.getCell(cell.getKey(), cell.getValue()) & RIGHT_BIT) == 0) {
            neighbours.add(new AbstractMap.SimpleEntry<>(cell.getKey(), cell.getValue() + 1));
        }

        return neighbours;
    }

    private Map.Entry<Integer, Integer> chooseRandomNeighbour(List<Map.Entry<Integer, Integer>> neighbours) {
        int index = random.nextInt(neighbours.size());
        return neighbours.get(index);
    }

    private int[][] convertRouteToArray() {
        int[][] routeArray = new int[route.size()][2];
        for (int i = 0; i < route.size(); i++) {
            routeArray[i][X] = route.get(i).getKey();
            routeArray[i][Y] = route.get(i).getValue();
        }
        return routeArray;
    }
}

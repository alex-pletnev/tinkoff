package edu.project2;

import edu.project2.exceptions.CoordinatesException;
import edu.project2.exceptions.RouteException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class MazeNavigator {
    private static final int BOTTOM_BIT = 1;
    private static final int LEFT_BIT = 2;
    private static final int TOP_BIT = 4;
    private static final int RIGHT_BIT = 8;
    private static final int X = 0;
    private static final int Y = 1;
    private final int[][] maze;
    private final boolean[][] visited;
    private final Deque<Cell> stack;
    private final List<Cell> route;

    public MazeNavigator(int[][] maze) {
        this.maze = maze;
        this.visited = new boolean[maze.length][maze[0].length];
        this.stack = new ArrayDeque<>();
        this.route = new ArrayList<>();
    }

    public int[][] navigateMaze(int x1, int y1, int x2, int y2) {
        if (x1 < 0 || x1 >= maze.length || y1 < 0 || y1 >= maze[0].length ||
            x2 < 0 || x2 >= maze.length || y2 < 0 || y2 >= maze[0].length) {
            throw new CoordinatesException("Coordinates must be positive!");
        }

        Cell initialCell = new Cell(x1, y1);
        markAsVisited(initialCell);
        stack.push(initialCell);

        while (!stack.isEmpty()) {
            Cell currentCell = stack.pop();

            if (currentCell.row() == x2 && currentCell.col() == y2) {
                route.add(currentCell);
                return convertRouteToArray();
            }

            List<Cell> unvisitedNeighbours = getUnvisitedNeighbours(currentCell);

            if (!unvisitedNeighbours.isEmpty()) {
                route.add(currentCell);
                stack.push(currentCell);
                Cell chosenCell = chooseRandomNeighbour(unvisitedNeighbours);
                markAsVisited(chosenCell);
                stack.push(chosenCell);
            } else {
                if (route.isEmpty()) {
                    throw new RouteException("No route found");
                }
                route.remove(route.size() - 1);
            }
        }

        throw new RouteException("No route found");
    }

    private void markAsVisited(Cell cell) {
        visited[cell.row()][cell.col()] = true;
    }

    private List<Cell> getUnvisitedNeighbours(Cell cell) {
        List<Cell> neighbours = new ArrayList<>();

        if (cell.row() > 0 && !visited[cell.row() - 1][cell.col()] &&
            (maze[cell.row()][cell.col()] & TOP_BIT) == 0) {
            neighbours.add(new Cell(cell.row() - 1, cell.col()));
        }
        if (cell.row() < maze.length - 1 && !visited[cell.row() + 1][cell.col()] &&
            (maze[cell.row()][cell.col()] & BOTTOM_BIT) == 0) {
            neighbours.add(new Cell(cell.row() + 1, cell.col()));
        }
        if (cell.col() > 0 && !visited[cell.row()][cell.col() - 1] &&
            (maze[cell.row()][cell.col()] & LEFT_BIT) == 0) {
            neighbours.add(new Cell(cell.row(), cell.col() - 1));
        }
        if (cell.col() < maze[0].length - 1 && !visited[cell.row()][cell.col() + 1] &&
            (maze[cell.row()][cell.col()] & RIGHT_BIT) == 0) {
            neighbours.add(new Cell(cell.row(), cell.col() + 1));
        }

        return neighbours;
    }

    private Cell chooseRandomNeighbour(List<Cell> neighbours) {
        Random random = new Random();
        int index = random.nextInt(neighbours.size());
        return neighbours.get(index);
    }

    private int[][] convertRouteToArray() {
        int[][] routeArray = new int[route.size()][2];
        for (int i = 0; i < route.size(); i++) {
            routeArray[i][X] = route.get(i).row();
            routeArray[i][Y] = route.get(i).col();
        }
        return routeArray;
    }
}

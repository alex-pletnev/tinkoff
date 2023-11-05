package edu.project2;

import edu.project2.exceptions.CoordinatesException;
import edu.project2.exceptions.RouteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MazeNavigator {
    private final int[][] maze;
    private final boolean[][] visited;
    private final Stack<Cell> stack;
    private final List<Cell> route;

    public MazeNavigator(int[][] maze) {
        this.maze = maze;
        this.visited = new boolean[maze.length][maze[0].length];
        this.stack = new Stack<>();
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

            if (currentCell.getRow() == x2 && currentCell.getCol() == y2) {
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
        visited[cell.getRow()][cell.getCol()] = true;
    }

    private List<Cell> getUnvisitedNeighbours(Cell cell) {
        List<Cell> neighbours = new ArrayList<>();

        if (cell.getRow() > 0 && !visited[cell.getRow() - 1][cell.getCol()] && (maze[cell.getRow()][cell.getCol()] & 4) == 0) {
            neighbours.add(new Cell(cell.getRow() - 1, cell.getCol()));
        }
        if (cell.getRow() < maze.length - 1 && !visited[cell.getRow() + 1][cell.getCol()] && (maze[cell.getRow()][cell.getCol()] & 1) == 0) {
            neighbours.add(new Cell(cell.getRow() + 1, cell.getCol()));
        }
        if (cell.getCol() > 0 && !visited[cell.getRow()][cell.getCol() - 1] && (maze[cell.getRow()][cell.getCol()] & 2) == 0) {
            neighbours.add(new Cell(cell.getRow(), cell.getCol() - 1));
        }
        if (cell.getCol() < maze[0].length - 1 && !visited[cell.getRow()][cell.getCol() + 1] && (maze[cell.getRow()][cell.getCol()] & 8) == 0) {
            neighbours.add(new Cell(cell.getRow(), cell.getCol() + 1));
        }

        return neighbours;
    }

    private Cell chooseRandomNeighbour(List<Cell> neighbours) {
        int index = (int) (Math.random() * neighbours.size());
        return neighbours.get(index);
    }

    private int[][] convertRouteToArray() {
        int[][] routeArray = new int[route.size()][2];
        for (int i = 0; i < route.size(); i++) {
            routeArray[i][0] = route.get(i).getRow(); //x
            routeArray[i][1] = route.get(i).getCol(); //y
        }
        return routeArray;
    }
}

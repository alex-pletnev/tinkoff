package edu.project2;

import edu.project2.exceptions.CoordinatesException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class MazeGenerator {

    private static final int BOTTOM_BIT = 1;
    private static final int LEFT_BIT = 2;
    private static final int TOP_BIT = 4;
    private static final int RIGHT_BIT = 8;
    private final int rows;
    private final int cols;
    private final int[][] maze;
    private final boolean[][] visited;
    private final Deque<Cell> stack;

    public MazeGenerator(int rows, int cols) {

        if (rows <= 0 || cols <= 0) {
            throw new CoordinatesException("Num of rows ans cols must be positive and greater than 0!");
        }
        this.rows = rows;
        this.cols = cols;
        this.maze = new int[rows][cols];
        this.visited = new boolean[rows][cols];
        this.stack = new ArrayDeque<>();
    }

    private void initMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = BOTTOM_BIT | LEFT_BIT | TOP_BIT | RIGHT_BIT;
            }
        }
    }

    public void generateMaze() {
        initMaze();
        Cell initialCell = new Cell(0, 0);
        markAsVisited(initialCell);
        stack.push(initialCell);

        while (!stack.isEmpty()) {
            Cell currentCell = stack.pop();
            List<Cell> unvisitedNeighbours = getUnvisitedNeighbours(currentCell);

            if (!unvisitedNeighbours.isEmpty()) {
                stack.push(currentCell);
                Cell chosenCell = chooseRandomNeighbour(unvisitedNeighbours);
                removeWall(currentCell, chosenCell);
                markAsVisited(chosenCell);
                stack.push(chosenCell);
            }
        }
    }

    public int[][] getMaze() {
        return maze;
    }

    private void markAsVisited(Cell cell) {
        visited[cell.row()][cell.col()] = true;
    }

    private List<Cell> getUnvisitedNeighbours(Cell cell) {
        List<Cell> neighbours = new ArrayList<>();

        if (cell.row() > 0 && !visited[cell.row() - 1][cell.col()]) {
            neighbours.add(new Cell(cell.row() - 1, cell.col()));
        }
        if (cell.row() < rows - 1 && !visited[cell.row() + 1][cell.col()]) {
            neighbours.add(new Cell(cell.row() + 1, cell.col()));
        }
        if (cell.col() > 0 && !visited[cell.row()][cell.col() - 1]) {
            neighbours.add(new Cell(cell.row(), cell.col() - 1));
        }
        if (cell.col() < cols - 1 && !visited[cell.row()][cell.col() + 1]) {
            neighbours.add(new Cell(cell.row(), cell.col() + 1));
        }

        return neighbours;
    }

    private Cell chooseRandomNeighbour(List<Cell> neighbours) {
        Random random = new Random();
        int index = random.nextInt(neighbours.size());
        return neighbours.get(index);
    }

    private void removeWall(Cell cell1, Cell cell2) {
        int rowDiff = cell2.row() - cell1.row();
        int colDiff = cell2.col() - cell1.col();

        //bottom left top right

        if (rowDiff == -1) {
            maze[cell1.row()][cell1.col()] &= ~TOP_BIT;
            maze[cell2.row()][cell2.col()] &= ~BOTTOM_BIT;
        } else if (rowDiff == 1) {
            maze[cell1.row()][cell1.col()] &= ~BOTTOM_BIT;
            maze[cell2.row()][cell2.col()] &= ~TOP_BIT;
        } else if (colDiff == -1) {
            maze[cell1.row()][cell1.col()] &= ~LEFT_BIT;
            maze[cell2.row()][cell2.col()] &= ~RIGHT_BIT;
        } else if (colDiff == 1) {
            maze[cell1.row()][cell1.col()] &= ~RIGHT_BIT;
            maze[cell2.row()][cell2.col()] &= ~LEFT_BIT;
        }
    }
}

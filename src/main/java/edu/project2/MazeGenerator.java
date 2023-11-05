package edu.project2;

import edu.project2.exceptions.CoordinatesException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class MazeGenerator {
    private final int rows;
    private final int cols;
    private final int[][] maze;
    private final boolean[][] visited;
    private final Stack<Cell> stack;

    public MazeGenerator(int rows, int cols) {

        if (rows <= 0 || cols <= 0) {
            throw new CoordinatesException("Num of rows ans cols must be positive and greater than 0!");
        }
        this.rows = rows;
        this.cols = cols;
        this.maze = new int[rows][cols];
        this.visited = new boolean[rows][cols];
        this.stack = new Stack<>();
    }

    private void initMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = 15;
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
        visited[cell.getRow()][cell.getCol()] = true;
    }

    private List<Cell> getUnvisitedNeighbours(Cell cell) {
        List<Cell> neighbours = new ArrayList<>();

        if (cell.getRow() > 0 && !visited[cell.getRow() - 1][cell.getCol()]) {
            neighbours.add(new Cell(cell.getRow() - 1, cell.getCol()));
        }
        if (cell.getRow() < rows - 1 && !visited[cell.getRow() + 1][cell.getCol()]) {
            neighbours.add(new Cell(cell.getRow() + 1, cell.getCol()));
        }
        if (cell.getCol() > 0 && !visited[cell.getRow()][cell.getCol() - 1]) {
            neighbours.add(new Cell(cell.getRow(), cell.getCol() - 1));
        }
        if (cell.getCol() < cols - 1 && !visited[cell.getRow()][cell.getCol() + 1]) {
            neighbours.add(new Cell(cell.getRow(), cell.getCol() + 1));
        }

        return neighbours;
    }

    private Cell chooseRandomNeighbour(List<Cell> neighbours) {
        Random random = new Random();
        int index = random.nextInt(neighbours.size());
        return neighbours.get(index);
    }

    private void removeWall(Cell cell1, Cell cell2) {
        int rowDiff = cell2.getRow() - cell1.getRow();
        int colDiff = cell2.getCol() - cell1.getCol();

        //bottom left top right

        if (rowDiff == -1) {
            maze[cell1.getRow()][cell1.getCol()] &= ~4;
            maze[cell2.getRow()][cell2.getCol()] &= ~1;
        } else if (rowDiff == 1) {
            maze[cell1.getRow()][cell1.getCol()] &= ~1;
            maze[cell2.getRow()][cell2.getCol()] &= ~4;
        } else if (colDiff == -1) {
            maze[cell1.getRow()][cell1.getCol()] &= ~2;
            maze[cell2.getRow()][cell2.getCol()] &= ~8;
        } else if (colDiff == 1) {
            maze[cell1.getRow()][cell1.getCol()] &= ~8;
            maze[cell2.getRow()][cell2.getCol()] &= ~2;
        }
    }
}

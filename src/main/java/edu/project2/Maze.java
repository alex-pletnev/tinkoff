package edu.project2;

import edu.project2.exceptions.CellValueException;
import edu.project2.exceptions.CoordinatesException;

public class Maze {
    private static final int MIN_CELL_VALUE = 0b0000;
    private static final int MAX_CELL_VALUE = 0b1111;
    private final int rows;
    private final int cols;
    private int[][] maze;

    public Maze(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new CoordinatesException("Num of rows ans cols must be positive and greater than 0!");
        }
        this.rows = rows;
        this.cols = cols;
        this.maze = new int[rows][cols];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public void setCell(int row, int col, int newValue) {
        if (newValue < MIN_CELL_VALUE || newValue > MAX_CELL_VALUE) {
            throw new CellValueException();
        }
        maze[row][col] = newValue;
    }

    public int getCell(int row, int col) {
        return maze[row][col];
    }

    public void invertBitInCell(int row, int col, int bitToInvert) {
        maze[row][col] &= ~bitToInvert;
    }
}

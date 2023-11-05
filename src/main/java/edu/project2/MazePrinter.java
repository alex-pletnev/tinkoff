package edu.project2;

import org.apache.logging.log4j.Logger;

public class MazePrinter {
    private static final int BOTTOM_BIT = 1;
    private static final int LEFT_BIT = 2;
    private static final int TOP_BIT = 4;
    private static final int RIGHT_BIT = 8;
    private static final int BRICK_SIZE = 3;
    private final int[][] maze;
    private final String[][] strMaze;
    private final Logger logger;

    public MazePrinter(int[][] maze, Logger logger) {
        this.maze = maze;
        this.strMaze = new String[maze.length * BRICK_SIZE][maze[0].length * BRICK_SIZE];
        this.logger = logger;
    }

    private void initStrMaze() {
        for (int i = 0; i < strMaze.length; i++) {
            for (int j = 0; j < strMaze[0].length; j++) {
                if (i == 0 || j == 0 || i == strMaze.length - 1 || j == strMaze[0].length - 1) {
                    strMaze[i][j] = "██";
                } else {
                    strMaze[i][j] = "  ";
                }
            }
        }
    }

    public String[][] fillStrMaze() {
        initStrMaze();
        int strI = 1;
        int srtJ;
        for (int i = 0; i < maze.length; i++) {
            strI += i * BRICK_SIZE;
            srtJ = 1;
            for (int j = 0; j < maze[0].length; j++) {
                var cell = maze[i][j];
                srtJ += j * BRICK_SIZE;
                if ((cell & BOTTOM_BIT) == BOTTOM_BIT) {
                    strMaze[strI + 1][srtJ - 1] = "██";
                    strMaze[strI + 1][srtJ] = "██";
                    strMaze[strI + 1][srtJ + 1] = "██";
                }
                if ((cell & LEFT_BIT) == LEFT_BIT) {
                    strMaze[strI + 1][srtJ - 1] = "██";
                    strMaze[strI][srtJ - 1] = "██";
                    strMaze[strI - 1][srtJ - 1] = "██";
                }
                if ((cell & TOP_BIT) == TOP_BIT) {
                    strMaze[strI - 1][srtJ - 1] = "██";
                    strMaze[strI - 1][srtJ] = "██";
                    strMaze[strI - 1][srtJ + 1] = "██";
                }
                if ((cell & RIGHT_BIT) == RIGHT_BIT) {
                    strMaze[strI + 1][srtJ + 1] = "██";
                    strMaze[strI][srtJ + 1] = "██";
                    strMaze[strI - 1][srtJ + 1] = "██";
                }
                srtJ -= j * 3;
            }
            strI -= i * 3;

        }
        return strMaze;
    }


    public void printMaze() {
        StringBuilder yCoords = new StringBuilder();
        yCoords.append("      ");
        for (int i = 0; i < maze.length; i++) {
            yCoords.append(i).append("     ");
        }
        logger.info(yCoords);
        for (int i = 0; i < strMaze.length; i++) {
            StringBuilder row = new StringBuilder();
            if (i % BRICK_SIZE == 1) {
                if (i / BRICK_SIZE < 10) {
                    row.append(i / BRICK_SIZE).append("   ");
                } else {
                    row.append(i / BRICK_SIZE).append("  ");
                }

            } else {
                row.append("    ");
            }

            for (var cell : strMaze[i]) {
                row.append(cell);
            }
            logger.info(row);
        }
    }

    public void addPath(int[][] path) {
        for (int[] cords : path) {
            strMaze[cords[0] * BRICK_SIZE + 1][cords[1] * BRICK_SIZE + 1] = "##";
        }
        strMaze[path[0][0] * BRICK_SIZE + 1][path[0][1] * BRICK_SIZE + 1] = "AA";
        strMaze[path[path.length - 1][0] * BRICK_SIZE + 1][path[path.length - 1][1] * BRICK_SIZE + 1] = "BB";
    }


}



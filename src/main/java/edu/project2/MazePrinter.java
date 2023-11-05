package edu.project2;

import org.apache.logging.log4j.Logger;

public class MazePrinter {
    private final int[][] maze;
    private final String[][] strMaze;
    private final Logger logger;


    public MazePrinter(int[][] maze, Logger logger) {
        this.maze = maze;
        this.strMaze = new String[maze.length * 3][maze[0].length * 3];
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
            strI += i * 3;
            srtJ = 1;
            for (int j = 0; j < maze[0].length; j++) {
                var cell = maze[i][j];
                srtJ += j * 3;
                if ((cell & 1) == 1) { // bottom
                    strMaze[strI + 1][srtJ - 1] = "██";
                    strMaze[strI + 1][srtJ] = "██";
                    strMaze[strI + 1][srtJ + 1] = "██";
                }
                if ((cell & 2) == 2) { // left
                    strMaze[strI + 1][srtJ - 1] = "██";
                    strMaze[strI][srtJ - 1] = "██";
                    strMaze[strI - 1][srtJ - 1] = "██";
                }
                if ((cell & 4) == 4) { // top
                    strMaze[strI - 1][srtJ - 1] = "██";
                    strMaze[strI - 1][srtJ] = "██";
                    strMaze[strI - 1][srtJ + 1] = "██";
                }
                if ((cell & 8) == 8) { // right
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
            if (i % 3 == 1) {
                if (i / 3 < 10) {
                    row.append(i / 3).append("   ");
                } else {
                    row.append(i / 3).append("  ");
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
            strMaze[cords[0] * 3 + 1][cords[1] * 3 + 1] = "##";
        }
        strMaze[path[0][0] * 3 + 1][path[0][1] * 3 + 1] = "AA";
        strMaze[path[path.length - 1][0] * 3 + 1][path[path.length - 1][1] * 3 + 1] = "BB";
    }


}



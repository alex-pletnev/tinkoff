package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int X1 = 0;
    private static final int Y1 = 0;
    private static final int X2 = 4;
    private static final int Y2 = 5;

    private Main() {
    }

    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

            Maze maze = new Maze(ROWS, COLS);
            MazeGenerator mazeGenerator = new MazeGenerator(maze);
            mazeGenerator.generateMaze();

            MazePrinter mazePrinter = new MazePrinter(maze, LOGGER);

            MazeNavigator mazeNavigator = new MazeNavigator(maze);
            try {
                var path = mazeNavigator.navigateMaze(X1, Y1, X2, Y2);
                mazePrinter.fillStrMaze();
                mazePrinter.addPath(path);
                mazePrinter.printMaze();

            } catch (RuntimeException e) {
                LOGGER.error("Impossible path!", e);
            }





    }
}

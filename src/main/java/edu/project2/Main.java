package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

            MazeGenerator mazeGenerator = new MazeGenerator(10, 10);
            mazeGenerator.generateMaze();
            var maze = mazeGenerator.getMaze();
            MazePrinter mazePrinter = new MazePrinter(maze, LOGGER);

            MazeNavigator mazeNavigator = new MazeNavigator(maze);
            try {
                var path = mazeNavigator.navigateMaze(0, 0, 4, 5);
                mazePrinter.fillStrMaze();
                mazePrinter.addPath(path);
                mazePrinter.printMaze();

            } catch (RuntimeException e) {
                LOGGER.info("Impossible path!");
            }





    }
}

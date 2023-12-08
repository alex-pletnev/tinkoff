package edu.project2;

import edu.project2.exceptions.CoordinatesException;
import edu.project2.exceptions.RouteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MazeNavigatorTest {

    @Test
    void navigateMazeSuccess() {
        int[][] mazeArr = {
            {14, 6, 5, 12, 6, 5, 12, 6, 5, 12},
            {3, 9, 14, 10, 3, 12, 10, 10, 6, 9},
            {6, 5, 8, 3, 12, 10, 10, 10, 3, 12},
            {10, 7, 9, 6, 9, 11, 3, 1, 13, 10},
            {3, 12, 6, 9, 6, 4, 5, 5, 5, 8},
            {6, 9, 10, 7, 9, 10, 6, 5, 12, 10},
            {2, 12, 3, 5, 12, 3, 9, 6, 9, 11},
            {10, 3, 5, 5, 9, 6, 12, 3, 5, 12},
            {10, 6, 5, 12, 7, 8, 3, 5, 12, 10},
            {3, 9, 7, 1, 5, 9, 7, 5, 1, 9}};
        var maze = new Maze(10, 10);
        maze.setMaze(mazeArr);

        MazeNavigator mazeNavigator = new MazeNavigator(maze);
        int[][] actual = mazeNavigator.navigateMaze(0, 0, 4, 5);
        int[][] excepted = {
            {0, 0},
            {1, 0},
            {1, 1},
            {0, 1},
            {0, 2},
            {0, 3},
            {1, 3},
            {2, 3},
            {2, 4},
            {3, 4},
            {3, 3},
            {4, 3},
            {4, 2},
            {5, 2},
            {6, 2},
            {6, 3},
            {6, 4},
            {7, 4},
            {7, 3},
            {7, 2},
            {7, 1},
            {6, 1},
            {6, 0},
            {7, 0},
            {8, 0},
            {9, 0},
            {9, 1},
            {8, 1},
            {8, 2},
            {8, 3},
            {9, 3},
            {9, 4},
            {9, 5},
            {8, 5},
            {7, 5},
            {7, 6},
            {8, 6},
            {8, 7},
            {8, 8},
            {9, 8},
            {9, 9},
            {8, 9},
            {7, 9},
            {7, 8},
            {7, 7},
            {6, 7},
            {6, 8},
            {5, 8},
            {5, 7},
            {5, 6},
            {6, 6},
            {6, 5},
            {5, 5},
            {4, 5},
        };
        Assertions.assertTrue(comparePaths(excepted, actual));
    }

    @Test
    void navigateMazeRouteException() {
        int[][] mazeArr = {
            {14, 6, 5, 12, 6, 5, 12, 6, 5, 12},
            {3, 9, 14, 10, 3, 12, 10, 10, 6, 9},
            {6, 5, 8, 3, 12, 10, 10, 10, 3, 12},
            {10, 7, 9, 6, 9, 11, 3, 1, 13, 10},
            {3, 12, 6, 9, 6, 4, 5, 5, 5, 8},
            {6, 9, 10, 7, 9, 10, 6, 5, 12, 10},
            {2, 12, 3, 5, 12, 3, 9, 6, 9, 11},
            {10, 3, 5, 5, 9, 6, 12, 3, 5, 12},
            {10, 6, 5, 12, 7, 8, 3, 5, 12, 10},
            {3, 9, 7, 1, 5, 9, 7, 5, 1, 9}};
        mazeArr[5][5] = 15;
        var maze = new Maze(10, 10);
        maze.setMaze(mazeArr);
        MazeNavigator mazeNavigator = new MazeNavigator(maze);
        Assertions.assertThrows(RouteException.class, () -> {
            mazeNavigator.navigateMaze(0, 0, 4, 5);
        });
    }

    @Test
    void navigateMazeCoordinateException() {
        int[][] mazeArr = {
            {14, 6, 5, 12, 6, 5, 12, 6, 5, 12},
            {3, 9, 14, 10, 3, 12, 10, 10, 6, 9},
            {6, 5, 8, 3, 12, 10, 10, 10, 3, 12},
            {10, 7, 9, 6, 9, 11, 3, 1, 13, 10},
            {3, 12, 6, 9, 6, 4, 5, 5, 5, 8},
            {6, 9, 10, 7, 9, 10, 6, 5, 12, 10},
            {2, 12, 3, 5, 12, 3, 9, 6, 9, 11},
            {10, 3, 5, 5, 9, 6, 12, 3, 5, 12},
            {10, 6, 5, 12, 7, 8, 3, 5, 12, 10},
            {3, 9, 7, 1, 5, 9, 7, 5, 1, 9}};
        var maze = new Maze(10, 10);
        maze.setMaze(mazeArr);
        MazeNavigator mazeNavigator = new MazeNavigator(maze);
        Assertions.assertThrows(CoordinatesException.class, () -> {
            mazeNavigator.navigateMaze(0, 0, 40, 5);
        });
    }


    boolean comparePaths(int[][] path1, int[][] path2) {
        if (path1.length != path2.length) {
            return false;
        }
        for (int i = 0; i < path1.length; i++) {
            boolean eq = path1[i][0] == path2[i][0] && path1[i][1] == path2[i][1];
            if (!eq) {
                return false;
            }
        }
        return true;
    }
}


package edu.project2;

import edu.project2.exceptions.CoordinatesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MazeGeneratorTest {

    @Test
    void MazeGeneratorWithNegativeSize() {
        Assertions.assertThrows(CoordinatesException.class, () -> new MazeGenerator(new Maze(-1, 5)));
    }

    @Test
    void MazeGeneratorWithNormalSize() {
        new MazeGenerator(new Maze(7, 5));
    }


}

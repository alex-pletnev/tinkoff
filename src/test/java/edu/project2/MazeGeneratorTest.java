package edu.project2;

import edu.project2.exceptions.CoordinatesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MazeGeneratorTest {

    @Test
    void MazeGeneratorWithNegativeSize() {
        Assertions.assertThrows(CoordinatesException.class, () -> new MazeGenerator(-1, 5));
    }

    @Test
    void MazeGeneratorWithNormalSize() {
        new MazeGenerator(7, 5);
    }


}

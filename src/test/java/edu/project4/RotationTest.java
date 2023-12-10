package edu.project4;

import edu.project4.entity.Point;
import edu.project4.transfom.other.Rotation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RotationTest {

    @Test
    void testApplyWhenPointThenTransformedPoint() {

        double theta = Math.PI / 4;
        Rotation rotation = new Rotation(theta);
        Point originalPoint = new Point(1, 0);
        double expectedX = Math.cos(theta);
        double expectedY = Math.sin(theta);


        Point resultPoint = rotation.apply(originalPoint);

        assertEquals(expectedX, resultPoint.getX());
        assertEquals(expectedY, resultPoint.getY());
    }
}

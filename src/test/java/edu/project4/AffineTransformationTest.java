package edu.project4;

import edu.project4.entity.Point;
import edu.project4.transfom.other.AffineTransformation;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AffineTransformationTest {

    private AffineTransformation affineTransformation;

    @BeforeEach
    void setUp() {
        affineTransformation = AffineTransformation.create();
    }

    @Test
    @DisplayName("test the 'create' method when it generates a valid affinetransformation object.")
    void testCreateWhenValidThenReturnValidAffineTransformation() {
        assertTrue(AffineTransformation.validate(affineTransformation));
    }

    @Test
    @DisplayName(
        "test the 'createAffineTransformations' method when it generates a list of affinetransformation objects.")
    void testCreateAffineTransformationsWhenValidThenReturnListOfAffineTransformation() {
        int samples = 5;
        List<AffineTransformation> transformations = AffineTransformation.createAffineTransformations(samples);
        assertNotNull(transformations);
        assertEquals(samples, transformations.size());
        assertTrue(transformations.stream().allMatch(AffineTransformation::validate));
    }

    @Test
    @DisplayName("test the 'apply' method with a valid point object.")
    void testApplyWhenValidPointThenReturnValidPoint() {
        Point point = new Point(1, 1);
        Point result = affineTransformation.apply(point);
        assertNotNull(result);
        // Additional assertions can be added if there are specific expectations for the result
    }

    @Test
    @DisplayName("test the 'linearTransformation' method with a valid point object.")
    void testLinearTransformationWhenValidPointThenReturnValidPoint() {
        Point point = new Point(1, 1);
        Point result = affineTransformation.linearTransformation(point);
        assertNotNull(result);
        // Additional assertions can be added if there are specific expectations for the result
    }

    @Test
    @DisplayName("test the 'scalarProduct' method with two valid point objects.")
    void testScalarProductWhenValidPointsThenReturnValidDouble() {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(3, 4);
        double result = affineTransformation.scalarProduct(point1, point2);
        assertEquals(11, result);
    }

    @Test
    @DisplayName("test the 'translation' method with a valid point object.")
    void testTranslationWhenValidPointThenReturnValidPoint() {
        Point point = new Point(1, 1);
        Point result = affineTransformation.translation(point);
        assertNotNull(result);
        // Additional assertions can be added if there are specific expectations for the result
    }

    @Test
    @DisplayName("test the 'validate' method with a valid affinetransformation object.")
    void testValidateWhenValidThenReturnTrue() {
        assertTrue(AffineTransformation.validate(affineTransformation));
    }

    @Test
    @DisplayName("test the 'validate' method with an invalid affinetransformation object.")
    void testValidateWhenInvalidThenReturnFalse() {
        AffineTransformation invalidTransformation = new AffineTransformation(2, 2, 2, 2, 2, 2);
        assertFalse(AffineTransformation.validate(invalidTransformation));
    }
}

package edu.hw3;

import edu.hw3.task2.Task2;
import edu.hw3.task2.UnclusterizedStringException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task2Test {
    @Test
    @DisplayName("Test testClusterize")
    void testClusterize() {
        // Arrange
        Task2 task2 = new Task2();
        String input = "((()))(())()()(()())";
        String[] expected = {"((()))", "(())", "()", "()", "(()())"};

        // Act
        String[] actual = task2.clusterize(input);
        System.out.println(Arrays.toString(actual));
        // Assert
        assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Test UnclusterizedStringException")
    void testClusterizeUnclusterizedStringException() {
        Task2 task2 = new Task2();
        String input = "(()";

        assertThrows(UnclusterizedStringException.class, () -> task2.clusterize(input));
    }

    @Test
    @DisplayName("Test with an empty string")
    void testClusterizeWithEmptyString() {
        Task2 task2 = new Task2();
        String input = "";
        String[] expected = {};

        String[] actual = task2.clusterize(input);

        assertArrayEquals(expected, actual);
    }
}

package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FiltersTest {

    private Path path;

    @BeforeEach
    void setUp() throws IOException {
        path = Files.createTempFile("test3", ".tmp");
    }

    @Test
    void trueFiltersTest() throws IOException {
        Files.writeString(path, "Hello, world!");
        var filter = Filters.globMatches(".*\\.tmp")
            .and(Filters.fileExtensions("tmp"))
            .and(Filters.largerThen(1));
        var actual = filter.accept(path);
        Assertions.assertTrue(actual);
    }

    @Test
    void falseFiltersTest() throws IOException {
        var filter = Filters.readable
            .and(Filters.writable)
            .and(Filters.regular)
            .and(Filters.largerThen(100))
            .and(Filters.fileExtensions("tmp"))
            .and(Filters.magicNumber((char) 0x89, 'P', 'N', 'G'));
        var actual = filter.accept(path);
        Assertions.assertFalse(actual);
    }

}

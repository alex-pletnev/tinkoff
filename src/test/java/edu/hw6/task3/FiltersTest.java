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
        var filter = Filters.GLOB_MATCHES(".*\\.tmp")
            .and(Filters.FILE_EXTENSIONS("tmp"))
            .and(Filters.LARGER_THEN(1));
        var actual = filter.accept(path);
        Assertions.assertTrue(actual);
    }

    @Test
    void falseFiltersTest() throws IOException {
        var filter = Filters.READABLE
            .and(Filters.WRITABLE)
            .and(Filters.REGULAR)
            .and(Filters.LARGER_THEN(100))
            .and(Filters.FILE_EXTENSIONS("tmp"))
            .and(Filters.MAGIC_NUMBER((char) 0x89, 'P', 'N', 'G'));
        var actual = filter.accept(path);
        Assertions.assertFalse(actual);
    }

}

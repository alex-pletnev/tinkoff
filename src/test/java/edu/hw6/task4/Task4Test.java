package edu.hw6.task4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class Task4Test {

    @TempDir
    Path tempDir;

    @Test
    void createFileAndFillItCreateAndWriteTest() throws IOException {
        var exceptedContent = "Programming is learned by writing programs. ― Brian Kernighan";
        var filePath = tempDir.resolve("Brian Kernighan");
        Task4.createFileAndFillIt(filePath, exceptedContent);
        Scanner scanner = new Scanner(filePath);
        var actualContent = scanner.nextLine();

        Assertions.assertAll(
            () -> Assertions.assertTrue(Files.exists(filePath)),
            () -> Assertions.assertEquals(exceptedContent, actualContent)
        );
    }

    @Test
    void createFileAndFillItWithNullPathExceptNullPointerExceptionTest() {
        var exceptedContent = "Programming is learned by writing programs. ― Brian Kernighan";

        Assertions.assertThrows(NullPointerException.class, () -> Task4.createFileAndFillIt(null, exceptedContent));

    }

}

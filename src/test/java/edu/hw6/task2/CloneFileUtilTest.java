package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CloneFileUtilTest {

    @TempDir
    Path tempDir;

    @Test
    void testCloneFileWithNull() throws IOException {
        Path result = CloneFileUtil.cloneFile(null);
        Assertions.assertNull(result, "Expected null when path is null");
    }

    @Test
    void testCloneFileWhenFileDoesNotExist() throws IOException {
        Path path = tempDir.resolve("nonexistent.txt");
        Path result = CloneFileUtil.cloneFile(path);
        Assertions.assertNotNull(result, "Expected non-null result when file does not exist");
        Assertions.assertTrue(Files.exists(result), "Expected file to exist after cloning");
    }

    @Test
    void testCloneFileWhenFileExist() throws IOException, NullPointerException {
        var fileToCopyName = "Tinkoff Bank Biggest Secrett.txt";
        Path path = tempDir.resolve(fileToCopyName);
        Files.createFile(path);
        Path copy1 = CloneFileUtil.cloneFile(path);
        var name1 = copy1.getFileName().toString();
        var exceptedName1 = "Tinkoff Bank Biggest Secrett — копия.txt";
        Path copy2 = CloneFileUtil.cloneFile(path);
        var name2 = copy2.getFileName().toString();
        var exceptedName2 = "Tinkoff Bank Biggest Secrett — копия (2).txt";
        Path copy3 = CloneFileUtil.cloneFile(path);
        var name3 = copy3.getFileName().toString();
        var exceptedName3 = "Tinkoff Bank Biggest Secrett — копия (3).txt";
        Assertions.assertAll(
            () -> Assertions.assertEquals(exceptedName1, name1),
            () -> Assertions.assertEquals(exceptedName2, name2),
            () -> Assertions.assertEquals(exceptedName3, name3)
        );
        Files.deleteIfExists(path);
        Files.deleteIfExists(copy1);
        Files.deleteIfExists(copy2);
        Files.deleteIfExists(copy3);
    }

    @Test
    void testCloneFileWithFileContent() throws IOException {
        Path path = tempDir.resolve("tmpFile");
        var excepted = "42";
        Files.writeString(path, excepted);
        Path copy = CloneFileUtil.cloneFile(path);
        Scanner scanner = new Scanner(copy);
        var actual = scanner.nextLine();
        Assertions.assertEquals(excepted, actual);
        Files.deleteIfExists(path);
        Files.deleteIfExists(copy);
    }
}

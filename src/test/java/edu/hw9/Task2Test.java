package edu.hw9;

import edu.hw9.task2.SearchDirsWithTargetNumOfFiles;
import edu.hw9.task2.SearchFilesWithPredicate;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task2Test {

    @TempDir
    File tempDir;

    private File createDirectoryWithFiles(String dirName, int numFiles) throws IOException {
        File directory = new File(tempDir, dirName);
        assertTrue(directory.mkdir());
        for (int i = 0; i < numFiles; i++) {
            File newFile = new File(directory, "file" + i + ".txt");
            assertTrue(newFile.createNewFile());
        }
        return directory;
    }

    @Test
    void testComputeWhenDirectoryContainsMoreThanTargetFiles()
        throws IOException {
        int target = 1000;
        File directory = createDirectoryWithFiles("dirWithMoreThanTarget", target + 1);
        SearchDirsWithTargetNumOfFiles task = new SearchDirsWithTargetNumOfFiles(directory, target);
        List<File> result;

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            result = forkJoinPool.invoke(task);
        }

        assertEquals(1, result.size());
        assertTrue(result.contains(directory));
    }

    @Test
    void testComputeWhenDirectoryContainsExactlyTargetFilesThen() throws IOException {
        int target = 3;
        File directory = createDirectoryWithFiles("dirWithTarget", target);
        SearchDirsWithTargetNumOfFiles task = new SearchDirsWithTargetNumOfFiles(directory, target);
        List<File> result;

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            result = forkJoinPool.invoke(task);
        }

        assertTrue(result.isEmpty());
    }

    @Test
    void testComputeWhenDirectoryContainsLessThanTargetFilesThen() throws IOException {
        int target = 5;
        File directory = createDirectoryWithFiles("dirWithLessThanTarget", target - 1);
        SearchDirsWithTargetNumOfFiles task = new SearchDirsWithTargetNumOfFiles(directory, target);
        List<File> result;

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            result = forkJoinPool.invoke(task);
        }

        assertTrue(result.isEmpty());
    }

    @Test
    void testComputeWhenDirectoryIsEmpty() {
        Predicate<File> predicate = file -> file.getName().endsWith(".txt");
        SearchFilesWithPredicate searchFilesWithPredicate = new SearchFilesWithPredicate(tempDir, predicate);
        List<File> result;

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            result = forkJoinPool.invoke(searchFilesWithPredicate);
        }
        assertTrue(result.isEmpty(), "The result should be an empty list when the directory is empty.");
    }

    @Test
    void testComputeWhenDirectoryContainsFiles() throws IOException {
        File file1 = new File(tempDir, "test1.txt");
        File file2 = new File(tempDir, "test2.json");
        File file3 = new File(tempDir, "test3.txt");
        Predicate<File> predicate = file -> file.getName().endsWith(".txt");
        List<File> excepted = new ArrayList<>();
        excepted.add(file1);
        excepted.add(file3);

        Files.createFile(file1.toPath());
        Files.createFile(file2.toPath());
        Files.createFile(file3.toPath());
        SearchFilesWithPredicate searchFilesWithPredicate = new SearchFilesWithPredicate(tempDir, predicate);
        List<File> result;

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            result = forkJoinPool.invoke(searchFilesWithPredicate);
        }

        result.sort(Comparator.naturalOrder());
        assertEquals(excepted, result);
    }

    @Test
    void testComputeWhenDirectoryContainsFilesLengthPr() throws IOException {
        // Arrange
        File file1 = new File(tempDir, "test1.txt");
        File file2 = new File(tempDir, "test2.txt");
        File file3 = new File(tempDir, "test3.txt");
        File file4 = new File(tempDir, "test4.txt");
        Predicate<File> predicate = file -> file.length() > 1000;

        Files.createFile(file1.toPath());
        Files.createFile(file2.toPath());
        Files.createFile(file3.toPath());
        Files.createFile(file4.toPath());
        SearchFilesWithPredicate searchFilesWithPredicate = new SearchFilesWithPredicate(tempDir, predicate);
        List<File> result;

        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            result = forkJoinPool.invoke(searchFilesWithPredicate);
        }

        assertTrue(result.isEmpty());
    }

}

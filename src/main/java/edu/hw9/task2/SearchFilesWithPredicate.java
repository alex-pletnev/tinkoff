package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class SearchFilesWithPredicate extends RecursiveTask<List<File>> {
    private final File directory;
    private final transient Predicate<File> predicate;

    public SearchFilesWithPredicate(File directory, Predicate<File> predicate) {
        this.directory = directory;
        this.predicate = predicate;
    }

    @Override
    protected List<File> compute() {
        File[] files = directory.listFiles();
        if (files == null) {
            return new ArrayList<>();
        }

        List<SearchFilesWithPredicate> subtasks = new ArrayList<>();
        List<File> foundFiles = new ArrayList<>();

        for (File file : files) {
            if (file.isDirectory()) {
                SearchFilesWithPredicate subtask = new SearchFilesWithPredicate(file, predicate);
                subtasks.add(subtask);
                subtask.fork();
            } else {
                if (predicate.test(file)) {
                    foundFiles.add(file);
                }
            }
        }

        for (SearchFilesWithPredicate subtask : subtasks) {
            foundFiles.addAll(subtask.join());
        }

        return foundFiles;
    }

}

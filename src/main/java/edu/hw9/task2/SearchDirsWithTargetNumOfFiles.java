package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SearchDirsWithTargetNumOfFiles extends RecursiveTask<List<File>> {
    private final File directory;
    private final int target;

    public SearchDirsWithTargetNumOfFiles(File directory, int target) {
        this.directory = directory;
        this.target = target;
    }

    @Override
    protected List<File> compute() {
        File[] files = directory.listFiles();
        if (files == null) {
            return new ArrayList<>();
        }

        List<SearchDirsWithTargetNumOfFiles> subtasks = new ArrayList<>();
        List<File> directoriesWithMoreThanTargetFiles = new ArrayList<>();

        int count = files.length;

        if (count > target) {
            directoriesWithMoreThanTargetFiles.add(directory);
        }

        for (File file : files) {
            if (file.isDirectory()) {
                SearchDirsWithTargetNumOfFiles subtask = new SearchDirsWithTargetNumOfFiles(file, target);
                subtasks.add(subtask);
                subtask.fork();
            }
        }

        for (SearchDirsWithTargetNumOfFiles subtask : subtasks) {
            directoriesWithMoreThanTargetFiles.addAll(subtask.join());
        }

        return directoriesWithMoreThanTargetFiles;
    }
}

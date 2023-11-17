package edu.hw6.task3;

import java.io.FileReader;
import java.nio.file.Files;

public class Filters {

    private Filters() {
    }

    public static AbstractFilter READABLE = Files::isReadable;

    public static AbstractFilter WRITABLE = Files::isWritable;

    public static AbstractFilter REGULAR = Files::isRegularFile;

    public static AbstractFilter LARGER_THEN(int size) {
        return entry -> Files.size(entry) > size;
    }

    public static AbstractFilter FILE_EXTENSIONS(String goalExtensions) {
        return entry -> {
            var actualExtensions = entry.getFileName().toString().split("\\.")[1];
            return goalExtensions.equals(actualExtensions);
        };
    }

    public static AbstractFilter GLOB_MATCHES(String regex) {
        return entry -> entry.getFileName().toString().matches(regex);
    }

    public static AbstractFilter MAGIC_NUMBER(char... magicNumbers) {
        return entry -> {
            try (var fileReader = new FileReader(entry.toFile())) {
                for (var magicNumber : magicNumbers) {
                    var c = fileReader.read();
                    if (magicNumber != c) {
                        return false;
                    }
                }

            }
            return true;
        };
    }

}

package edu.hw6.task3;

import java.io.FileReader;
import java.nio.file.Files;

public class Filters {

    private Filters() {
    }

    public static AbstractFilter readable = Files::isReadable;

    public static AbstractFilter writable = Files::isWritable;

    public static AbstractFilter regular = Files::isRegularFile;

    public static AbstractFilter largerThen(int size) {
        return entry -> Files.size(entry) > size;
    }

    public static AbstractFilter fileExtensions(String goalExtensions) {
        return entry -> {
            var actualExtensions = entry.getFileName().toString().split("\\.")[1];
            return goalExtensions.equals(actualExtensions);
        };
    }

    public static AbstractFilter globMatches(String regex) {
        return entry -> entry.getFileName().toString().matches(regex);
    }

    public static AbstractFilter magicNumber(char... magicNumbers) {
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

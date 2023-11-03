package edu.hw4;

import java.util.List;
import java.util.regex.Pattern;

public class Task13 {

    public List<Animal> run(List<Animal> list) {
        Pattern pattern = Pattern.compile("\\w+ \\w+ \\w+");
        return list.stream()
            .filter(animal -> pattern.matcher(animal.name()).find())
            .toList();
    }
}

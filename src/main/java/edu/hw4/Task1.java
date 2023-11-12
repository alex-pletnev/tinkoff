package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task1 {

    public List<Animal> run(List<Animal> list) {
        return list.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }
}

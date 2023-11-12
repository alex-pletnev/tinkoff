package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task2 {

    public List<Animal> run(List<Animal> list, int k) {
        return list.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }
}

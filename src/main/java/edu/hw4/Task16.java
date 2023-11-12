package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task16 {

    public List<Animal> run(List<Animal> list) {
        return list.stream()
            .sorted(
                Comparator.comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name)
            )
            .toList();
    }
}

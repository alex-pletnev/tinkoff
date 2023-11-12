package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Task18 {

    public Animal run(List<List<Animal>> listOfLists) {
        return listOfLists.stream()
            .map(list -> list.stream()
                .filter(animal -> animal.type() == Animal.Type.FISH)
                .max(Comparator.comparing(Animal::weight)).orElse(null))
            .filter(animal -> !Objects.isNull(animal))
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }
}

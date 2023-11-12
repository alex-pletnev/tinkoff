package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task8 {

    public Animal run(List<Animal> list, int k) {
        if (k < 0) {
            return null;
        }

        return list.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);

    }
}

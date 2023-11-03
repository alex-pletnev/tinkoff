package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task7 {

    public Animal run(List<Animal> list, int k) {
        if (list.size() <= k || k < 0) {
            return null;
        }

        return list.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed())
            .skip(k)
            .findFirst()
            .orElse(null);
    }
}

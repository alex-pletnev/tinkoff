package edu.hw4;

import java.util.List;

public class Task11 {
    private static final int HEIGHT_LIMIT = 100;

    public List<Animal> run(List<Animal> list) {
        return list.stream()
            .filter(Animal::bites)
            .filter(animal -> animal.height() > HEIGHT_LIMIT)
            .toList();
    }

}

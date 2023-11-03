package edu.hw4;

import java.util.List;

public class Task10 {

    public List<Animal> run(List<Animal> list) {
        return list.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }
}

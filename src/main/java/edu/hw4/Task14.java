package edu.hw4;

import java.util.List;

public class Task14 {

    public Boolean run(List<Animal> list, int k) {
        return list.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG)
            .anyMatch(animal -> animal.height() > k);
    }
}

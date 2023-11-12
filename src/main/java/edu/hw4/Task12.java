package edu.hw4;

import java.util.List;

public class Task12 {

    public Integer run(List<Animal> list) {
        return Math.toIntExact(list.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count());
    }

}

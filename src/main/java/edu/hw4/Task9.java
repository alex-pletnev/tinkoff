package edu.hw4;

import java.util.List;

public class Task9 {

    public Integer run(List<Animal> list) {
        return list.stream()
            .map(Animal::paws)
            .reduce(0, Integer::sum);

    }
}

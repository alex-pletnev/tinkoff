package edu.hw4;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Task4 {

    public Animal run(List<Animal> list) {
        Optional<Animal> maxAnimal = list.stream()
            .max(
                (a0, a1) -> {
                    var l0 = a0.name().length();
                    var l1 = a1.name().length();
                    return Integer.compare(l0, l1);
                });

        if (maxAnimal.isPresent()) {
            return maxAnimal.get();
        } else {
            throw new NoSuchElementException("List is empty");
        }
    }
}

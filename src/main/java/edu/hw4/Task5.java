package edu.hw4;

import java.util.List;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Task5 {

    public Animal.Sex run(List<Animal> list) {
        var map = list.stream()
            .collect(groupingBy(Animal::sex, counting()));
        if (map.get(Animal.Sex.M) > map.get(Animal.Sex.F)) {
            return Animal.Sex.M;
        } else {
            return Animal.Sex.F;
        }

    }
}

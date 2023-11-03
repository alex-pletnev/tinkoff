package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.toMap;

public class Task6 {
    public Map<Animal.Type, Animal> run(List<Animal> list) {
        return list.stream()
            .collect(groupingBy(Animal::type, maxBy(Comparator.comparingInt(Animal::weight))))
            .entrySet().stream()
            .collect(toMap(Map.Entry::getKey, entry -> entry.getValue().get()));

    }

}

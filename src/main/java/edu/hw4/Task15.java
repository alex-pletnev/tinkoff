package edu.hw4;

import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class Task15 {

    public Map<Animal.Type, Integer> run(List<Animal> list, int k, int l) {
        return list.stream()
            .filter(animal -> animal.age() >= k && animal.age() <= l)
            .collect(groupingBy(Animal::type, summingInt(Animal::weight)));

    }
}

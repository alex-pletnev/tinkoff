package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import static java.util.stream.Collectors.groupingBy;

public class Task17 {

    public Boolean run(List<Animal> list) {
        Map<Animal.Type, List<Animal>> animalMap = list.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG || animal.type() == Animal.Type.SPIDER)
            .collect(groupingBy(Animal::type));
        double spiderProbability = calculateProbability(animalMap, Animal.Type.SPIDER);
        double dogProbability = calculateProbability(animalMap, Animal.Type.DOG);
        if (spiderProbability == -1 || dogProbability == -1) {
            return false;
        }
        return spiderProbability > dogProbability;
    }

    double calculateProbability(Map<Animal.Type, List<Animal>> animalMap, Animal.Type type) {
        var animalList = animalMap.get(type);
        if (Objects.isNull(animalList)) {
            return -1;
        }
        var countTrue = 0;
        for (var animal : animalList) {
            if (animal.bites()) {
                countTrue++;
            }
        }
        return (double) countTrue / (double) animalList.size();

    }
}

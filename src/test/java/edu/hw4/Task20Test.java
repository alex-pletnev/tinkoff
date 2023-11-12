package edu.hw4;

import edu.hw4.validate.AgeError;
import edu.hw4.validate.HeightError;
import edu.hw4.validate.ValidationError;
import edu.hw4.validate.WeightError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task20Test {
    @Test
    void runTest() {
        Task19 task19 = new Task19();
        Task20 task20 = new Task20();
        Animal animal1 = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, -3, -10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);
        Animal animal3 = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 2, 3, -1, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 5, 1, false);
        Animal animal5 = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 1, 2, 1, true);
        List<Animal> data = new ArrayList<>();
        data.add(animal1);
        data.add(animal2);
        data.add(animal3);
        data.add(animal4);
        data.add(animal5);
        Map<String, Set<ValidationError>> excepted = new HashMap<>();
        Set<ValidationError> ex1 = new HashSet<>();
        ex1.add(new AgeError());
        ex1.add(new HeightError());
        Set<ValidationError> ex2 = new HashSet<>();
        ex2.add(new WeightError());
        excepted.put("Tom", ex1);
        excepted.put("Nemo", ex2);
        var ans = task19.run(data);
        var task20ans = task20.run(ans);
        for (var key : task20ans.keySet()) {
            System.out.println(key + ": " + task20ans.get(key));
        }
        Assertions.assertEquals(excepted, ans);
    }
}

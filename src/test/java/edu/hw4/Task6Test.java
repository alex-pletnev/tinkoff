package edu.hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task6Test {
    @Test
    void runTest() {
        Task6 task6 = new Task6();
        Animal animal1 = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.CAT, Animal.Sex.M, 5, 15, 20, true);
        Animal animal3 = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 2, 3, 0, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 5, 1, false);
        Animal animal5 = new Animal("Charlotte", Animal.Type.FISH, Animal.Sex.F, 1, 2, 10, true);
        List<Animal> data = new ArrayList<>();
        data.add(animal1);
        data.add(animal2);
        data.add(animal3);
        data.add(animal4);
        data.add(animal5);
        Map<Animal.Type, Animal> excepted = new HashMap<>();
        excepted.put(Animal.Type.CAT, animal2);
        excepted.put(Animal.Type.FISH, animal5);
        excepted.put(Animal.Type.BIRD, animal4);

        var ans = task6.run(data);
        Assertions.assertEquals(excepted, ans);
    }
}

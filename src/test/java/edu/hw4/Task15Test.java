package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task15Test {

    @Test
    void runTest() {
        Task15 task15 = new Task15();
        Animal animal1 = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.CAT, Animal.Sex.M, 5, 15, 20, true);
        Animal animal3 = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 2, 3, 10, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.SPIDER, Animal.Sex.F, 1, 5, 1, false);
        Animal animal5 = new Animal("Charlotte", Animal.Type.DOG, Animal.Sex.F, 1, 2, 0, true);
        List<Animal> data = new ArrayList<>();
        int k = 2;
        int l = 5;
        data.add(animal1);
        data.add(animal2);
        data.add(animal3);
        data.add(animal4);
        data.add(animal5);
        Map<Animal.Type, Integer> ans = task15.run(data, k, l);
        Map<Animal.Type, Integer> excepted = new TreeMap<>();
        excepted.put(Animal.Type.CAT, 25);
        excepted.put(Animal.Type.FISH, 10);
        Assertions.assertEquals(excepted, ans);
    }
}

package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task10Test {

    @Test
    void runTest() {
        Task10 task10 = new Task10();
        Animal animal1 = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 4, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 4, 15, 20, true);
        Animal animal3 = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 2, 3, 0, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 5, 1, false);
        Animal animal5 = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 1, 2, 0, true);
        List<Animal> data = new ArrayList<>();
        data.add(animal1);
        data.add(animal2);
        data.add(animal3);
        data.add(animal4);
        data.add(animal5);
        List<Animal> excepted = new ArrayList<>();
        excepted.add(animal3);
        excepted.add(animal4);
        excepted.add(animal5);
        List<Animal> ans = task10.run(data);

        Assertions.assertEquals(excepted, ans);
    }

    @Test
    void runTestEmpty() {
        Task10 task10 = new Task10();
        List<Animal> data = new ArrayList<>();
        List<Animal> excepted = new ArrayList<>();

        List<Animal> ans = task10.run(data);

        Assertions.assertEquals(excepted, ans);
    }
}

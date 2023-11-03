package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task8Test {

    @Test
    void runTest() {
        Task8 task8 = new Task8();
        Animal animal1 = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 1, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 2, 15, 20, true);
        Animal animal3 = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 3, 1, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 4, 5, 3, false);
        Animal animal5 = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 5, 2, 0, true);
        List<Animal> data = new ArrayList<>();
        int k = 5;
        data.add(animal1);
        data.add(animal2);
        data.add(animal3);
        data.add(animal4);
        data.add(animal5);
        var ans = task8.run(data, k);
        Assertions.assertEquals(animal3, ans);
    }

    @Test
    void runTestNullList() {
        Task8 task8 = new Task8();
        List<Animal> data = new ArrayList<>();
        int k = 5;

        var ans = task8.run(data, k);
        Assertions.assertNull(ans);
    }

    @Test
    void runTestNegK() {
        Task8 task8 = new Task8();
        Animal animal1 = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 1, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 2, 15, 20, true);
        Animal animal3 = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 3, 0, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 4, 5, 1, false);
        Animal animal5 = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 5, 2, 0, true);
        List<Animal> data = new ArrayList<>();
        int k = -5;
        data.add(animal1);
        data.add(animal2);
        data.add(animal3);
        data.add(animal4);
        data.add(animal5);
        var ans = task8.run(data, k);
        Assertions.assertNull(ans);
    }
}

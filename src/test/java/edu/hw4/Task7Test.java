package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task7Test {

    @Test
    void runTest() {
        Task7 task7 = new Task7();
        Animal animal1 = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 1, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 2, 15, 20, true);
        Animal animal3 = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 3, 0, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 4, 5, 1, false);
        Animal animal5 = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 5, 2, 0, true);
        List<Animal> data = new ArrayList<>();
        int k = 2;
        data.add(animal1);
        data.add(animal2);
        data.add(animal3);
        data.add(animal4);
        data.add(animal5);
        var ans = task7.run(data, k);
        Assertions.assertEquals(animal3, ans);
    }

    @Test
    void runTestNullList() {
        Task7 task7 = new Task7();
        List<Animal> data = new ArrayList<>();
        int k = 2;

        var ans = task7.run(data, k);
        Assertions.assertNull(ans);
    }

    @Test
    void runTestNegK() {
        Task7 task7 = new Task7();
        Animal animal1 = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 1, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 2, 15, 20, true);
        Animal animal3 = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 3, 0, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 4, 5, 1, false);
        Animal animal5 = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 5, 2, 0, true);
        List<Animal> data = new ArrayList<>();
        int k = -2;
        data.add(animal1);
        data.add(animal2);
        data.add(animal3);
        data.add(animal4);
        data.add(animal5);
        var ans = task7.run(data, k);
        Assertions.assertNull(ans);
    }

    @Test
    void runTestInvalidK() {
        Task7 task7 = new Task7();
        Animal animal1 = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 1, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 2, 15, 20, true);
        Animal animal3 = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 3, 0, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 4, 5, 1, false);
        Animal animal5 = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 5, 2, 0, true);
        List<Animal> data = new ArrayList<>();
        int k = 200;
        data.add(animal1);
        data.add(animal2);
        data.add(animal3);
        data.add(animal4);
        data.add(animal5);
        var ans = task7.run(data, k);
        Assertions.assertNull(ans);
    }
}

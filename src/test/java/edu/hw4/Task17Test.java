package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task17Test {

    @Test
    void runTestTrue() {
        Task17 task17 = new Task17();
        Animal animal1 = new Animal("Tom", Animal.Type.DOG, Animal.Sex.M, 3, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, false);
        Animal animal3 = new Animal("Nemo", Animal.Type.DOG, Animal.Sex.M, 2, 3, 0, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.SPIDER, Animal.Sex.F, 1, 5, 1, false);
        Animal animal5 = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 1, 2, 0, true);
        List<Animal> data = new ArrayList<>();
        data.add(animal1);
        data.add(animal2);
        data.add(animal3);
        data.add(animal4);
        data.add(animal5);
        var ans = task17.run(data);
        Assertions.assertTrue(ans);
    }

    @Test
    void runTestFalse() {
        Task17 task17 = new Task17();
        Animal animal1 = new Animal("Tom", Animal.Type.DOG, Animal.Sex.M, 3, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);
        Animal animal3 = new Animal("Nemo", Animal.Type.DOG, Animal.Sex.M, 2, 3, 0, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.SPIDER, Animal.Sex.F, 1, 5, 1, false);
        Animal animal5 = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 1, 2, 0, true);
        List<Animal> data = new ArrayList<>();
        data.add(animal1);
        data.add(animal2);
        data.add(animal3);
        data.add(animal4);
        data.add(animal5);
        var ans = task17.run(data);
        Assertions.assertFalse(ans);
    }

    @Test
    void runTestFalseWithNotEnoughData() {
        Task17 task17 = new Task17();
        Animal animal1 = new Animal("Tom", Animal.Type.DOG, Animal.Sex.M, 3, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);
        Animal animal3 = new Animal("Nemo", Animal.Type.DOG, Animal.Sex.M, 2, 3, 0, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.FISH, Animal.Sex.F, 1, 5, 1, false);
        Animal animal5 = new Animal("Charlotte", Animal.Type.FISH, Animal.Sex.F, 1, 2, 0, true);
        List<Animal> data = new ArrayList<>();
        data.add(animal1);
        data.add(animal2);
        data.add(animal3);
        data.add(animal4);
        data.add(animal5);
        var ans = task17.run(data);
        Assertions.assertFalse(ans);
    }

}



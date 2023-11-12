package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task12Test {

    @Test
    void runTest() {
        Task12 task12 = new Task12();
        Animal animal1 = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);
        Animal animal3 = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 2, 3, 0, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 5, 1, false);
        Animal animal5 = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 1, 2, 0, true);
        List<Animal> data = new ArrayList<>();
        data.add(animal1);
        data.add(animal2);
        data.add(animal3);
        data.add(animal4);
        data.add(animal5);
        var excepted = 1;

        var ans = task12.run(data);
        Assertions.assertEquals(excepted, ans);
    }
}

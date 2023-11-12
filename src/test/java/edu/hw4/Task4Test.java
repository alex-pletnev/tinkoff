package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task4Test {
    @Test
    void runTest() {
        Task4 task4 = new Task4();
        Animal animal1 = new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 3, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.CAT, Animal.Sex.M, 5, 15, 20, true);
        Animal animal3 = new Animal("Nemo", Animal.Type.CAT, Animal.Sex.M, 2, 3, 0, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.DOG, Animal.Sex.F, 1, 5, 1, false);
        Animal animal5 = new Animal("Charlotte", Animal.Type.DOG, Animal.Sex.F, 1, 2, 0, true);
        List<Animal> data = new ArrayList<>();
        data.add(animal1);
        data.add(animal2);
        data.add(animal3);
        data.add(animal4);
        data.add(animal5);
        Animal ans = task4.run(data);
        Assertions.assertEquals(animal5, ans);
    }

    @Test
    void runEmptyTest() {
        Task4 task4 = new Task4();
        List<Animal> data = new ArrayList<>();

        Assertions.assertThrows(NoSuchElementException.class, () -> task4.run(data));
    }

}

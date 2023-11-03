package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class Task18Test {

    @Test
    void runTest() {
        Task18 task18 = new Task18();
        Animal animal1 = new Animal("Tom", Animal.Type.FISH, Animal.Sex.M, 3, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.FISH, Animal.Sex.M, 5, 15, 20, true);
        List<Animal> list1 = new ArrayList<>();
        list1.add(animal1);
        list1.add(animal2);
        Animal animal3 = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 2, 3, 31, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 5, 21, false);
        List<Animal> list2 = new ArrayList<>();
        list1.add(animal3);
        list1.add(animal4);
        Animal animal5 = new Animal("Charlotte", Animal.Type.FISH, Animal.Sex.F, 1, 2, 10, true);
        Animal animal6 = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 1, 2, 100, true);
        List<Animal> list3 = new ArrayList<>();
        list1.add(animal5);
        list1.add(animal6);

        List<List<Animal>> data = new ArrayList<>();
        data.add(list1);
        data.add(list2);
        data.add(list3);


        var ans = task18.run(data);
        Assertions.assertEquals(animal3, ans);
    }

    @Test
    void runTestEmptyList() {
        Task18 task18 = new Task18();
        Animal animal1 = new Animal("Tom", Animal.Type.FISH, Animal.Sex.M, 3, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.FISH, Animal.Sex.M, 5, 15, 20, true);
        List<Animal> list1 = new ArrayList<>();
        list1.add(animal1);
        list1.add(animal2);
        Animal animal3 = new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 2, 3, 31, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 5, 21, false);
        List<Animal> list2 = new ArrayList<>();
        list1.add(animal3);
        list1.add(animal4);
        Animal animal5 = new Animal("Charlotte", Animal.Type.FISH, Animal.Sex.F, 1, 2, 10, true);
        Animal animal6 = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 1, 2, 100, true);
        List<Animal> list3 = new ArrayList<>();
        list1.add(animal5);
        list1.add(animal6);
        List<Animal> list4 = new ArrayList<>();


        List<List<Animal>> data = new ArrayList<>();
        data.add(list1);
        data.add(list2);
        data.add(list3);
        data.add(list4);

        var ans = task18.run(data);
        Assertions.assertEquals(animal3, ans);
    }

    @Test
    void runTestWithNoFish() {
        Task18 task18 = new Task18();
        Animal animal1 = new Animal("Tom", Animal.Type.DOG, Animal.Sex.M, 3, 10, 5, true);
        Animal animal2 = new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 5, 15, 20, true);
        List<Animal> list1 = new ArrayList<>();
        list1.add(animal1);
        list1.add(animal2);
        Animal animal3 = new Animal("Nemo", Animal.Type.CAT, Animal.Sex.M, 2, 3, 31, false);
        Animal animal4 = new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 1, 5, 21, false);
        List<Animal> list2 = new ArrayList<>();
        list1.add(animal3);
        list1.add(animal4);
        Animal animal5 = new Animal("Charlotte", Animal.Type.CAT, Animal.Sex.F, 1, 2, 10, true);
        Animal animal6 = new Animal("Charlotte", Animal.Type.SPIDER, Animal.Sex.F, 1, 2, 100, true);
        List<Animal> list3 = new ArrayList<>();
        list1.add(animal5);
        list1.add(animal6);


        List<List<Animal>> data = new ArrayList<>();
        data.add(list1);
        data.add(list2);
        data.add(list3);


        var ans = task18.run(data);
        Assertions.assertNull(ans);
    }

}

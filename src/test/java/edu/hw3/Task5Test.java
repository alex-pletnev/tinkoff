package edu.hw3;

import edu.hw3.task5.Person;
import edu.hw3.task5.Task5;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Task5Test {

    @ParameterizedTest
    @MethodSource("correctPersonsLists")
    void parseContactsSimpleTest(List<Person> personList, String mode, List<Person> excepted) {
        Task5 task5 = new Task5();
        var result = task5.parseContacts(personList, mode);

        Assertions.assertThat(result).isEqualTo(excepted);
    }

    @Test
    void parseContactsEmptyTest() {
        List<Person> personList = new ArrayList<>();
        var mode = "DESC";
        List<Person> excepted = new ArrayList<>();

        Task5 task5 = new Task5();
        var result = task5.parseContacts(personList, mode);

        Assertions.assertThat(result).isEqualTo(excepted);
    }

    @Test
    void parseContactsNullTest() {
        List<Person> personList = null;
        var mode = "DESC";
        List<Person> excepted = new ArrayList<>();

        Task5 task5 = new Task5();
        var result = task5.parseContacts(personList, mode);

        Assertions.assertThat(result).isEqualTo(excepted);
    }

    private static Stream<Arguments> correctPersonsLists() {
        ArrayList<Person> list1 = new ArrayList<>();
        list1.add(new Person("John", "Locke"));
        list1.add(new Person("Thomas", "Aquinas"));
        list1.add(new Person("David", "Hume"));
        list1.add(new Person("Rene", "Descartes"));

        ArrayList<Person> list1Excepted = new ArrayList<>();
        list1Excepted.add(new Person("Thomas", "Aquinas"));
        list1Excepted.add(new Person("Rene", "Descartes"));
        list1Excepted.add(new Person("David", "Hume"));
        list1Excepted.add(new Person("John", "Locke"));

        ArrayList<Person> list2 = new ArrayList<>();
        list2.add(new Person("Paul", "Erdos"));
        list2.add(new Person("Leonhard", "Euler"));
        list2.add(new Person("Carl", "Gauss"));

        ArrayList<Person> list2Excepted = new ArrayList<>();
        list2Excepted.add(new Person("Carl", "Gauss"));
        list2Excepted.add(new Person("Leonhard", "Euler"));
        list2Excepted.add(new Person("Paul", "Erdos"));

        return Stream.of(
            Arguments.of(list1, "ASC", list1Excepted),
            Arguments.of(list2, "DESC", list2Excepted)
        );
    }
}

package edu.hw3.task5;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public record Person(String name, String surname) implements Comparable<Person> {

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(surname, person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public int compareTo(@NotNull Person o) {
        int tmp = surname.compareTo(o.surname);
        if (tmp == 0) {
            return (name.compareTo(o.name));
        }
        return tmp;
    }

    @Override public String toString() {
        return name + " " + surname;
    }
}

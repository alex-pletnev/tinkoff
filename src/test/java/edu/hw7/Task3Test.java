package edu.hw7;

import edu.hw7.task3.CachedPersonDatabase;
import edu.hw7.task3.LockPersonDatabase;
import edu.hw7.task3.Person;
import edu.hw7.task3.PersonDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class Task3Test {
    private static final CachedPersonDatabase cachedPersonDatabase;
    private static final LockPersonDatabase lockPersonDatabase;

    static {
        cachedPersonDatabase = new CachedPersonDatabase(new ArrayList<>());
        cachedPersonDatabase.add(new Person(0, "Alex", "asd 23", "8999777"));
        cachedPersonDatabase.add(new Person(1, "Bob", "sdee 34", "12345654567"));

        lockPersonDatabase = new LockPersonDatabase(new ArrayList<>());
        lockPersonDatabase.add(new Person(0, "Alex", "asd 23", "8999777"));
        lockPersonDatabase.add(new Person(1, "Bob", "sdee 34", "12345654567"));
    }

    @ParameterizedTest
    @MethodSource("personDatabaseFactory")
    void synchronizedFindsTests(PersonDatabase personDatabase) {
        var fName = personDatabase.findByName("Alex");
        var fAddress = personDatabase.findByAddress("asd 23");
        var fPhoneNumber = personDatabase.findByPhone("8999777");

        List<Person> excepted = new ArrayList<>();
        excepted.add(new Person(0, "Alex", "asd 23", "8999777"));
        Assertions.assertAll(
            () -> Assertions.assertEquals(excepted, fName),
            () -> Assertions.assertEquals(excepted, fAddress),
            () -> Assertions.assertEquals(excepted, fPhoneNumber)
        );
    }

    static Stream<PersonDatabase> personDatabaseFactory() {
        List<PersonDatabase> personDatabaseList = new ArrayList<>();
        personDatabaseList.add(cachedPersonDatabase);
        personDatabaseList.add(lockPersonDatabase);
        return personDatabaseList.stream();
    }
}

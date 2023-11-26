package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CachedPersonDatabase implements PersonDatabase {
    private final List<Person> personDatabase;
    private final Map<Integer, Person> idIndex;
    private final Map<String, List<Person>> nameIndex;
    private final Map<String, List<Person>> addressIndex;
    private final Map<String, List<Person>> phoneIndex;

    public CachedPersonDatabase(List<Person> personDatabase) {
        this.personDatabase = personDatabase;
        this.idIndex = new HashMap<>();
        this.nameIndex = new HashMap<>();
        this.addressIndex = new HashMap<>();
        this.phoneIndex = new HashMap<>();
    }

    @Override
    public synchronized void add(Person person) {
        if (Objects.isNull(person)) {
            throw new NullPointerException();
        }
        personDatabase.add(person);
        addToIndexes(person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = idIndex.get(id);
        if (Objects.isNull(person)) {
            throw new NullPointerException();
        }
        personDatabase.remove(id);
        removeFromIndexes(person);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return nameIndex.getOrDefault(name, new ArrayList<>());
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return addressIndex.getOrDefault(address, new ArrayList<>());
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return phoneIndex.getOrDefault(phone, new ArrayList<>());
    }

    private void addToIndexes(Person person) {
        idIndex.put(person.id(), person);
        addToIndex(nameIndex, person.name(), person);
        addToIndex(addressIndex, person.address(), person);
        addToIndex(phoneIndex, person.phoneNumber(), person);
    }

    private void removeFromIndexes(Person person) {
        idIndex.remove(person.id());
        removeFromIndex(nameIndex, person.name(), person);
        removeFromIndex(addressIndex, person.address(), person);
        removeFromIndex(phoneIndex, person.phoneNumber(), person);
    }

    private void addToIndex(Map<String, List<Person>> index, String key, Person person) {
        List<Person> persons = index.computeIfAbsent(key, k -> new ArrayList<>());
        persons.add(person);
    }

    private void removeFromIndex(Map<String, List<Person>> index, String key, Person person) {
        List<Person> persons = index.get(key);
        if (persons != null) {
            persons.remove(person);
            if (persons.isEmpty()) {
                index.remove(key);
            }
        }
    }
}

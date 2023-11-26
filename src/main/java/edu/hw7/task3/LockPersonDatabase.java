package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockPersonDatabase implements PersonDatabase {
    private final List<Person> personDatabase;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final Map<Integer, Person> idIndex;
    private final Map<String, List<Person>> nameIndex;
    private final Map<String, List<Person>> addressIndex;
    private final Map<String, List<Person>> phoneIndex;

    public LockPersonDatabase(List<Person> personDatabase) {
        this.personDatabase = personDatabase;
        this.idIndex = new HashMap<>();
        this.nameIndex = new HashMap<>();
        this.addressIndex = new HashMap<>();
        this.phoneIndex = new HashMap<>();
    }

    @Override
    public void add(Person person) {
        if (Objects.isNull(person)) {
            throw new NullPointerException();
        }
        writeLock.lock();
        try {
            personDatabase.add(person);
            addToIndexes(person);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void delete(int id) {
        Person person = idIndex.get(id);
        if (Objects.isNull(person)) {
            throw new NullPointerException();
        }
        writeLock.lock();
        try {
            personDatabase.remove(id);
            removeFromIndexes(person);
        } finally {
            writeLock.unlock();
        }
    }

    public List<Person> findByName(String name) {
        List<Person> persons;
        readLock.lock();
        try {
            persons = nameIndex.getOrDefault(name, new ArrayList<>());
        } finally {
            readLock.unlock();
        }
        return persons;
    }

    @Override
    public List<Person> findByAddress(String address) {
        List<Person> persons;
        readLock.lock();
        try {
            persons = addressIndex.getOrDefault(address, new ArrayList<>());
        } finally {
            readLock.unlock();
        }
        return persons;
    }

    @Override
    public List<Person> findByPhone(String phone) {
        List<Person> persons;
        readLock.lock();
        try {
            persons = phoneIndex.getOrDefault(phone, new ArrayList<>());
        } finally {
            readLock.unlock();
        }
        return persons;
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

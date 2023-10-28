package edu.hw3.task8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<E> implements Iterator<E> {
    private Collection<E> collection;
    private List<E> list;
    private int count;

    public BackwardIterator(Collection<E> collection) {
        this.collection = collection;
        list = new ArrayList<>(collection);
        count = collection.size() - 1;
    }

    public Collection<E> getCollection() {
        return collection;
    }

    public void setCollection(Collection<E> collection) {
        this.collection = collection;
        list = new ArrayList<>(collection);
    }

    @Override
    public boolean hasNext() {
        return count >= 0;
    }

    @Override
    public E next() {
        if (hasNext()) {
            return list.get(count--);
        } else {
            throw new NoSuchElementException();
        }
    }
}

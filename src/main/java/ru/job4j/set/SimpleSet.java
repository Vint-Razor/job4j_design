package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set;

    public SimpleSet() {
        set = new SimpleArrayList<>(16);
    }

    @Override
    public boolean add(T value) {
        boolean added = false;
        if (!contains(value) || value == null) {
            set.add(value);
            added = true;
        }
        return added;
    }

    @Override
    public boolean contains(T value) {
        T element = null;
        for (T el : set) {
            if (Objects.equals(el, value)) {
                element = el;
                break;
            }
        }
        return Objects.equals(element, value);
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}

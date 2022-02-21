package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set;

    public SimpleSet() {
        set = new SimpleArrayList<>(16);
    }

    @Override
    public boolean add(T value) {
        boolean flag = false;
        if (!contains(value)) {
            set.add(value);
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean contains(T value) {
        for (T el : set) {
            if (value.equals(el)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}

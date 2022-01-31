package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;
    private  int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        int capacity = container.length;
        if (size == capacity) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size++] = value;
    }

    @Override
    public T set(int index, T newValue) {
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T get(int index) {
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }
}

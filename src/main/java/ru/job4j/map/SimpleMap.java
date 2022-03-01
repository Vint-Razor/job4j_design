package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        MapEntry<K, V> newEntry = new MapEntry<>(key, value);
        boolean rsl = false;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = newEntry;
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(key.hashCode()));
        return table[i] != null && key.equals(table[i].key) ? table[i].value : null;
    }

    @Override
    public boolean remove(K key) {
        int i = indexFor(hash(key.hashCode()));
        boolean rsl = false;
        if (table[i] != null && key.equals(table[i].key)) {
            table[i] = null;
            modCount++;
            count--;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        int concurrentCount = modCount;
        return new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                if (concurrentCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length && table[index] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (table.length - 1) & hash;
    }

    private void expand() {
        MapEntry<K, V>[] oldTab = Arrays.copyOf(table, capacity);
        capacity *= 2;
        table = new MapEntry[capacity];
        count = 0;
        for (MapEntry<K, V> el : oldTab) {
            if (el != null) {
                put(el.key, el.value);
            }
        }
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

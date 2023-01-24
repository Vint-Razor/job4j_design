package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        final SoftReference<V> softReference = new SoftReference<>(value);
        cache.put(key, softReference);
    }

    public V get(K key) {
        final SoftReference<V> reference = cache.get(key);
        return reference.get();
    }

    protected abstract V load(K key);
}

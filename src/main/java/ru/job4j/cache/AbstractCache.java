package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
/**
 * Абстрактный класс эмулирующий поведение кеша. Программа
 * должна считывать данные из источника и выдавать их при запросе ключа.
 * Если в кеше ключа нет. Кеш должен загрузить себе данные. По умолчанию
 * в кеше нет ни чего.
 */
public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    /**
     * Метод загружает содержимое по ключу в кэш. Если ключ уже
     * существует в кэше, то старое значение заменяется новым.
     * @param key - ключ, по которому будет храниться значение
     * @param value - значение
     */
    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    /**
     * Метод возвращает значение по ключу. Если ключа в кэше
     * нет, то загружает по этому ключу значение в кэш.
     * @param key ключ
     * @return значение
     */
    public V get(K key) {
        return cache.getOrDefault(key, new SoftReference<>(load(key))).get();
    }

    protected abstract V load(K key);
}

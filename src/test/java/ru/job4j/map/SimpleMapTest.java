package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutThenTrue() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 2));
    }

    @Test
    public void whenPutDuplicateKeyThenFalse() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 2);
        assertFalse(map.put(1, 3));
    }

    @Test
    public void whenCollisionIndexPutThenFalse() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("one", "Ivan");
        map.put("two", "Fedor");
        assertFalse(map.put("three", "Sergey"));
    }

    @Test
    public void whenGetValidKeyThenValue() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("one", "Ivan");
        assertThat(map.get("one"), is("Ivan"));
    }

    @Test
    public void whenGetInvalidNullIndexKeyThenNull() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("one", "Ivan");
        map.put("two", "Fedor");
        assertNull(map.get("five"));
    }

    @Test
    public void whenGetInvalidCollisionKeyThenNull() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("one", "Ivan");
        map.put("two", "Fedor");
        assertNull(map.get("three"));
    }

    @Test
    public void whenRemoveValidKeyThenTrue() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("one", "Ivan");
        map.put("two", "Fedor");
        assertTrue(map.remove("one"));
    }

    @Test
    public void whenRemoveInvalidKeyThenFalse() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("one", "Ivan");
        map.put("two", "Fedor");
        assertFalse(map.remove("four"));
    }

    @Test
    public void whenGetRemoveElThenNull() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.put("one", "Ivan");
        map.put("two", "Fedor");
        map.remove("one");
        assertNull(map.get("one"));
    }

    @Test
    public void whenIterator4ElemThenNext() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 5);
        map.put(2, 6);
        map.put(3, 7);
        map.put(4, 8);
        Iterator<Integer> iterator = map.iterator();
        assertThat(iterator.next(), is(1));
        iterator.next();
        iterator.next();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(4));
        assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorHasNextFalseThenException() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 5);
        map.put(2, 6);
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModMapAfterIteratorThenException() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 5);
        map.put(2, 6);
        Iterator<Integer> iterator = map.iterator();
        map.put(3, 5);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyMapIteratorNextThenException() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
    }

    @Test
    public void whenExpandMap8ElThenMap16() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 5);
        map.put(2, 6);
        map.put(3, 7);
        map.put(4, 8);
        map.put(5, 11);
        assertThat(map.get(5), is(11));
        map.put(6, 12);
        map.put(7, 33);
        map.put(8, 55);
        map.put(9, 55);
        map.put(10, 55);
        assertThat(map.get(5), is(11));
    }
}
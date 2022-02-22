package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenContainsThreeThenTrue() {
        Set<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        assertTrue(set.contains("three"));
    }

    @Test
    public void whenContainsFourThenFalse() {
        Set<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        assertFalse(set.contains("four"));
    }

    @Test
    public void whenIteratorThenIterator() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        Iterator<Integer> iterator = set.iterator();
        iterator.next();
        iterator.next();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(3));
    }
}
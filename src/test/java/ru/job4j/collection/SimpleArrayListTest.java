package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.list.List;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SimpleArrayListTest {

    List<Integer> list;
    @Before
    public void initDate() {
        list = new SimpleArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThenSizeIncrease() {
        assertEquals(3, list.size());
    }

    @Test
    public void whenAddAndGetByCorrectIndex() {
        assertEquals(Integer.valueOf(1), list.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAndGetByIncorrectIndexThenException() {
        list.get(5);
    }

    @Test
    public void whenRemoveThenGenValueAndSizeDecrease() {
        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(2), list.remove(1));
        assertEquals(2, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveByIncorrectIndexThenGetException() {
        list.remove(5);
    }

    @Test
    public void whenRemoveThenMustNotByEmpty() {
        list.remove(1);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test
    public void whenAddNullThenBeSameBehavior() {
        list = new SimpleArrayList<>(3);
        list.add(null);
        list.add(null);
        assertEquals(2, list.size());
        assertNull(list.get(0));
        assertNull(list.get(1));
    }

    @Test
    public void whenSetThenGetOldValueAndSizeNotChanged() {
        assertEquals(Integer.valueOf(2), list.set(1, 22));
        assertEquals(3, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetByIncorrectIndexGetException() {
        list.set(5, 22);
    }

    @Test
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        list = new SimpleArrayList<>(5);
        list.iterator().next();
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        assertEquals(Integer.valueOf(1), list.iterator().next());
        assertEquals(Integer.valueOf(1), list.iterator().next());
    }

    @Test
    public void whenCheckedIterator() {
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(3), iterator.next());
    }

    @Test
    public void whenNoPlaceMustIncreaseCapacity() {
        IntStream.range(3, 10).forEach(v -> list.add(v));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(4);
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(0);
        iterator.next();
    }
}
package ru.job4j.collection.list;

import java.util.Iterator;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> first;
    private Node<E> last;
    private int size = 0;

    public SimpleLinkedList() {
    }

    @Override
    public void add(E value) {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }

    private static class Node<E> {
        private Node<E> next;
        private Node<E> prev;
        private E data;

        public Node(Node<E> next, Node<E> prev, E data) {
            this.next = next;
            this.prev = prev;
            this.data = data;
        }
    }
}


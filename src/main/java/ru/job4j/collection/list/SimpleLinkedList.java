package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    public SimpleLinkedList() {
    }

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = first;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.data;
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = first;
            Node<E> oldNode;
            final int concurModCount = modCount;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (concurModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                oldNode = node;
                node = node.next;
                return oldNode.data;
            }
        };
    }
}


package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int[] arr : data) {
            for (Integer num : arr) {
                if (num != null) {
                    return column < data.length;
                }
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (hasNext()) {
            while (row < data[column].length) {
                return data[column][row++];
            }
            column++;
            row = 0;
        }
        return null;
    }
}

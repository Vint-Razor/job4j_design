package ru.job4j.io;

import org.junit.Assert;
import org.junit.Test;

public class MatrixTest {

    @Test
    public void multiply() {
        int size = 2;
        int[][] result = Matrix.multiply(size);
        int[][] expected = {
                {1, 2},
                {2, 4}
        };
        Assert.assertArrayEquals(expected, result);
    }
}
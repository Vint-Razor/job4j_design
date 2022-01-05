package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void when3Plus4Then7() {
        int a = 3;
        int b = 4;
        int actual = 7;
        int rsl = Calculator.sum(a, b);
        assertEquals(actual, rsl);
    }
}
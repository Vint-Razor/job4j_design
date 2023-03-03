package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MaxMinTest {
    private final List<Integer> list = Arrays.asList(1, 4, 5, 3, 7, 8, 6);
    private final MaxMin maxMin = new MaxMin();

    @Test
    void checkMax() {
        Integer actual = 8;
        var expected = maxMin.max(list, Comparator.naturalOrder());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkMin() {
        Integer actual = 1;
        var expected = maxMin.min(list, Comparator.naturalOrder());
        assertThat(actual).isEqualTo(expected);
    }
}
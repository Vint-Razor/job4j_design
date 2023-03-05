package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MaxMinTest {

    @Test
    void checkMax() {
        List<Integer> list = Arrays.asList(1, 4, 5, 3, 7, 8, 6);
        MaxMin maxMin = new MaxMin();
        Integer actual = 8;
        var expected = maxMin.max(list, Comparator.naturalOrder());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkMin() {
        List<Integer> list = Arrays.asList(1, 4, 5, 3, 7, 8, 6);
        MaxMin maxMin = new MaxMin();
        Integer actual = 1;
        var expected = maxMin.min(list, Comparator.naturalOrder());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenListEmptyThenNull() {
        List<Integer> list = new ArrayList<>();
        MaxMin maxMin = new MaxMin();
        var expected = maxMin.min(list, Comparator.naturalOrder());
        assertThat(expected).isNull();
    }
}
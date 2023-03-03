package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = (x -> x < 0);
        return loop(value, comparator, predicate);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = (x -> x > 0);
        return loop(value, comparator, predicate);
    }

    private <T> T loop(List<T> values, Comparator<T> comparator, Predicate<Integer> predicate) {
        T result = values.get(0);
        for (T val : values) {
            result = predicate.test(comparator.compare(result, val)) ? val : result;
        }
        return result;
    }
}

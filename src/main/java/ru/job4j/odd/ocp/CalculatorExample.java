package ru.job4j.odd.ocp;

import java.util.function.BiFunction;

public class CalculatorExample {

    private static class SimpleCalculator {

        public int sum(int a, int b) {
            return a + b;
        }
    }

    private static class SubtractionSimpleCalculator extends SimpleCalculator {



    }

    private static class AbstractCalculator<T> {
        public T calculate(BiFunction<T, T, T> function, T first, T second) {
            return function.apply(first, second);
        }
    }

    public static void main(String[] args) {
        AbstractCalculator<Integer> integerAbstractCalculator = new AbstractCalculator<>();
        Integer rsl = integerAbstractCalculator.calculate(Integer::sum, 3, 7);
        System.out.println(rsl);
    }
}

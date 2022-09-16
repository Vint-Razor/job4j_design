package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        final SimpleConvert simpleConvert = new SimpleConvert();
        final List<String> list = simpleConvert
                .toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .allSatisfy(e -> assertThat(e).isNotBlank())
                .contains("five")
                .contains("second", Index.atIndex(1))
                .containsAnyOf("six", "seven", "first")
                .doesNotContain("zero", Index.atIndex(0))
                .startsWith("first")
                .endsWith("three", "four", "five");
    }

    @Test
    void checkSet() {
        final SimpleConvert simpleConvert = new SimpleConvert();
        final Set<String> set = simpleConvert
                .toSet("first", "first", "second", "three", "four", "five");
        assertThat(set).hasSize(5)
                .allSatisfy(e -> assertThat(e).isNotBlank())
                .contains("first")
                .doesNotContain("zero")
                .containsAnyOf("three", "five", "ten")
                .containsExactlyInAnyOrder("first", "second", "three", "four", "five");
    }

    @Test
    void toMap() {
        final SimpleConvert simpleConvert = new SimpleConvert();
        final Map<String, Integer> map = simpleConvert
                .toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsEntry("first", 0)
                .doesNotContainKey("zero")
                .doesNotContainValue(5)
                .containsKeys("first", "five")
                .containsValues(0, 1, 2, 3, 4);
    }
}
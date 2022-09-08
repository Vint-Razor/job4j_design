package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenKeyValThenKeyVal() {
        NameLoad nameLoad = new NameLoad();
        nameLoad.parse("key=val");
        String val = nameLoad.getMap().get("key");
        assertThat(val).isNotEmpty()
                .isNotBlank()
                .isEqualTo("val");
    }

    @Test
     void whenTwoArgsThenTwoVal() {
        NameLoad nameLoad = new NameLoad();
        nameLoad.parse("one=1", "two=2");
        String val = nameLoad.getMap().get("two");
        assertThat(val).isNotEmpty()
                .isNotBlank()
                .isEqualTo("2");
    }

    @Test
    void checkNonSymbolEqual() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key,val"))
                .hasMessageContaining("key,val")
                .hasMessageContaining("the symbol \"=\"")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkNotKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=val"))
                .hasMessageContaining("name: =val")
                .hasMessageContaining("not contain a key")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkNotValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key="))
                .hasMessageContaining("name: key=")
                .hasMessageContaining("not contain a value")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenEmptyArgsThenExcept() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .hasMessageMatching("Names array is empty")
                .isInstanceOf(IllegalArgumentException.class);
    }
}
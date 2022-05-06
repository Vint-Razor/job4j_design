package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenDoubleSignEquals() {
        String path = "./data/double_sign_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("value=1"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
    }

    @Test
    public void whenEmptyString() {
        String path = "./data/empty_string.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMissingKey() {
        String path = "./data/missing_key.properties";
        Config config = new Config(path);
        config.load();
        config.value("name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEmptyKey() {
        String path = "./data/empty_key.properties";
        Config config = new Config(path);
        config.load();
        config.value("name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEmptyValue() {
        String path = "./data/empty_value.properties";
        Config config = new Config(path);
        config.load();
        config.value("name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoEqualSign() {
        String path = "./data/absence_of_an_equal_sign.properties";
        Config config = new Config(path);
        config.load();
        config.value("name");
    }
}
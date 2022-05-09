package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .map(str -> str.split("=", 2))
                    .filter(arr -> !(arr.length == 1 && arr[0].isEmpty())
                            && arr[0].indexOf('#') != 0)
                    .forEach(arr -> {
                        validator(arr);
                        values.put(arr[0], arr[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validator(String[] arr) {
        if (arr.length == 1 || arr[0].isBlank() || arr[1].isBlank()) {
            throw new IllegalArgumentException(
                    "Отсутствует ключ, значение или разделитель \"=\"."
            );
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}

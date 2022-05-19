package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("the \"%s\" key is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String str : args) {
            if (!str.startsWith("-")) {
                throw new IllegalArgumentException("arguments must start with \"-\"");
            }
            String[] strings = str.substring(1)
                    .split("=", 2);
            validator(strings);
            values.put(strings[0], strings[1]);
        }
    }

    private void validator(String[] strings) {
        if (strings.length != 2 || strings[0].isBlank() || strings[1].isBlank()) {
            throw new IllegalArgumentException("There is no key, value or separator \"=\" in the argument");
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("At least one argument is needed");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
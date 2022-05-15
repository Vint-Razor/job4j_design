package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        duplicatesVisitor.getMap().forEach((k, value) -> {
            if (value.size() > 1) {
                System.out.printf("file \"%s\" is duplicate%n", k.getName());
                value.forEach(path -> System.out.println("\t" + path.toAbsolutePath()));
            }
        });
    }
}

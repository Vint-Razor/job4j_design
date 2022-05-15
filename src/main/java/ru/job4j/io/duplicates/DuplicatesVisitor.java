package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, Path> map = new HashMap<>();
    private Set<Path> paths = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty =
                new FileProperty(Files.size(file), file.getFileName().toString());
        if (map.containsKey(fileProperty)) {
            paths.add(map.get(fileProperty));
            paths.add(file);
        }
        map.put(fileProperty, file);
        return super.visitFile(file, attrs);
    }

    public Set<Path> getPaths() {
        return paths;
    }
}

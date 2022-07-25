package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
/**
 * Класс поиска файлов в файловой системе по придекату
 * */

public class Search {
    public static void main(String[] args) throws IOException {
        validator(args);
        Path start = Paths.get(args[0]);
        String find = args[1];
        search(start, p -> p.toFile()
                .getName()
                .endsWith(find))
                .forEach(System.out::println);
    }
    /**
     * Метод ищет файлы начиная с определенной директории по предикату
     * @param root стартовая директория
     * @param condition условия сравнения файлов
     * @return список путей фалов
     * */

    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);
        try {
            Files.walkFileTree(root, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPath();
    }

    private static void validator(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Root folder or file extension is null. Usage java -jar search.jar ROOT_FOLDER FILE_EXTENSION");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("The file extension must start with \".\"");
        }
        Path start = Paths.get(args[0]);
        if (!start.toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", start.toAbsolutePath()));
        }
        if (!start.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", start.toAbsolutePath()));
        }
    }
}

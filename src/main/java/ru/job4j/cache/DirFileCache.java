package ru.job4j.cache;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Класс для работы с файлами
 */
public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        validate(cachingDir);
        this.cachingDir = cachingDir;
    }

    private String readFile(Path path) {
        String str;
        if (!path.toString().endsWith(".txt")) {
            throw new IllegalArgumentException(String.format("%s файл должен иметь расширение \".txt\"", path));
        }
        try {
            str = Files.readString(path);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("%s файл не найден", path));
        }
        return str;
    }

    private void validate(String path) {
        File file = new File(path);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s не является папкой", path));
        }
    }

    /**
     * метод загружает в кэш содержимое файла, если нет такого ключа
     *
     * @param key это относительный путь к файлу в директории.
     * @return содержимое файла
     */
    @Override
    protected String load(String key) {
        return readFile(Path.of(cachingDir, key));
    }
}

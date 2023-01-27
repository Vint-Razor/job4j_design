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

    private String getFile(Path path) {
        String str;
        try {
            str = Files.readString(path);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("%s файл не найден", path));
        }
        if (!path.endsWith(".txt")) {
            throw new IllegalArgumentException(String.format("%s файл должен иметь расширение \".txt\"", str));
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
        return getFile(Path.of(cachingDir, key));
    }
}

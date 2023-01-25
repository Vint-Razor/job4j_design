package ru.job4j.cache;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    private String getFile(Path path) {
        String str;
        try {
            str = Files.readString(path);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("%s файл не найден", path));
        }
        return str;
    }

    private void validator(String path) {
        File file = new File(path);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s не является папкой", path));
        }
    }

    @Override
    protected String load(String key) {
        validator(cachingDir);
        return getFile(Path.of(cachingDir + "//" + key));
    }
}

package ru.job4j.cache;

import java.io.File;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
        load(cachingDir);
    }

    @Override
    protected String load(String key) {
        validator(cachingDir);
        return null;
    }

    private void validator(String path) {
        final File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("%s not found", path));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s not directory", path));
        }
    }
}

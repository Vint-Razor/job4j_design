package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

public class Emulator {

    public static void main(String[] args) {
        AbstractCache<String, String> abstractCache
                = new DirFileCache("./data/ConsoleChat");
        abstractCache.get("log.txt");
    }
}

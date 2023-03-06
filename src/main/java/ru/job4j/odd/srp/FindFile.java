package ru.job4j.odd.srp;

import java.nio.file.Path;

public class FindFile {

    public Path validate(Path path, String template) {
        return path.endsWith(template) ? null : path;
    }
}

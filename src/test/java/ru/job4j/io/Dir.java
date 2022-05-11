package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File dir = new File("c:\\projects\\job4j_design");
        if (!dir.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", dir.getAbsoluteFile()));
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", dir.getAbsoluteFile()));
        }
        System.out.printf("%s : %s bytes%n", dir.getAbsoluteFile(),
                dir.getTotalSpace());
        for (File subfile : dir.listFiles()) {
            System.out.printf("%s - size %d bytes%n", subfile.getName(), subfile.length());
        }
    }
}

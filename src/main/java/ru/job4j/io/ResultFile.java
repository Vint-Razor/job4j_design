package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("result.txt")
                ))) {
            out.println("New Hello World!");
            out.println("Hello");
            out.println("World!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        StringBuilder sb = new StringBuilder();
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            boolean error = false;
            for (String[] arr : readToList(source)) {
                if (!error && Integer.parseInt(arr[0]) >= 400) {
                    sb.append(arr[1]);
                    sb.append(";");
                    error = true;
                } else if (error && Integer.parseInt(arr[0]) < 400) {
                    sb.append(arr[1]);
                    sb.append(";");
                    error = false;
                    out.println(sb);
                    sb.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> readToList(String source) {
        LinkedList<String[]> list = new LinkedList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines()
                    .map(str -> str.split(" "))
                    .forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "target.csv");
    }
}

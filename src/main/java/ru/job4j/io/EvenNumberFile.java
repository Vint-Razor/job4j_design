package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int num;
            while ((num = in.read()) != -1) {
                boolean rsl = num % 2 == 0;
                System.out.println(rsl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

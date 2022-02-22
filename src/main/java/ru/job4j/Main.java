package ru.job4j;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String str = "hello world";
        System.out.println(str);
        System.out.println(binary(123));
    }

    public static void hashPrint(String str, Scanner scanner) {
        int num = scanner.nextInt();
        str = num + " " + str;
        System.out.println(str + " " + str.hashCode());
    }

    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }
}

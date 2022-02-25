package ru.job4j;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String str = "begin...";
        System.out.println(str);
        String[] arrStr = new String[10];
        String one = "one";
        String two = "two";
        String three = "three";
        addHash(arrStr, one);
        addHash(arrStr, two);
        addHash(arrStr, three);
        addHash(arrStr, "four");
        addHash(arrStr, "five");
        addHash(arrStr, "six");
        for (String el : arrStr) {
            System.out.println(el);
        }

        System.out.println("hesh \"one\" " + binary(one.hashCode()));
        System.out.println("&\n" + "arr length " + binary(arrStr.length));
        System.out.println("index      " + binary(one.hashCode() & arrStr.length));

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

    public static <T> void addHash(T[] arr, T value) {
        int lastNull = arr.length - 1;
        int i = (arr.length - 1) & value.hashCode();
        T p = arr[i];
        if (p == null) {
            arr[i] = value;
        } else {
            for (int j = lastNull; j >= 0; j--) {
                if (arr[j] == null) {
                    arr[j] = value;
                    break;
                }
            }
        }
    }
}

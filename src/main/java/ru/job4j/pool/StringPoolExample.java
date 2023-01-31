package ru.job4j.pool;

public class StringPoolExample {
    public static void main(String[] args) {
        String string1 = new String("Hello");
        char[] arrChar = {'H', 'e', 'l', 'l', 'o'};
        String string2 = new String(arrChar);
        System.out.println(string1 == string2);
        String str1 = "Hello";
        String str2 = "Hello";
        System.out.println(str1 == str2);
        System.out.println(string1);
        System.out.println(string2);
        System.out.println(str1);
        System.out.println(str2);
        String string3 = string2.intern();
        System.out.println(string3 == str1);
    }

}

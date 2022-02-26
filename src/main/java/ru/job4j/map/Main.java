package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        User one = new User("Jack", 1,
                new GregorianCalendar(1995, Calendar.APRIL, 30));
        User two = new User("Jack", 1,
                new GregorianCalendar(1995, Calendar.APRIL, 30));
        Map<User, Object> map = new HashMap<>();
        map.put(one, new Object());
        map.put(two, new Object());
        map.entrySet().stream()
                .forEach(System.out::println);
    }
}

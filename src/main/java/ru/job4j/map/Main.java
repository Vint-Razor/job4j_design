package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        map.put(new User("Jack", 1,
                new GregorianCalendar(1995, Calendar.APRIL, 30)), new Object());
        map.put(new User("Jack", 1,
                new GregorianCalendar(1995, Calendar.APRIL, 30)), new Object());
        map.put(new User("Jack", 1,
                new GregorianCalendar(1995, Calendar.APRIL, 30)), new Object());
        map.put(new User("Jack", 1,
                new GregorianCalendar(1995, Calendar.APRIL, 30)), new Object());
        map.put(new User("Jack", 1,
                new GregorianCalendar(1995, Calendar.APRIL, 30)), new Object());
        map.put(new User("Jack", 1,
                new GregorianCalendar(1995, Calendar.APRIL, 30)), new Object());
        map.put(new User("Jack", 1,
                new GregorianCalendar(1995, Calendar.APRIL, 30)), new Object());
        map.put(new User("Jack", 1,
                new GregorianCalendar(1995, Calendar.APRIL, 30)), new Object());
        map.put(new User("Jack", 1,
                new GregorianCalendar(1995, Calendar.APRIL, 30)), new Object());
        map.put(new User("Jack", 1,
                new GregorianCalendar(1995, Calendar.APRIL, 30)), new Object());
        map.entrySet().stream()
                .forEach(System.out::println);
    }
}

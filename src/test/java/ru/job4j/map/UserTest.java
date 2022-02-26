package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class UserTest {

    @Test
    public void whenGetBirthday() {
        User one = new User("Jack", 1,
                new GregorianCalendar(1995, Calendar.APRIL, 30));
        User two = new User("Jack", 1,
                new GregorianCalendar(1995, Calendar.APRIL, 30));
        Assert.assertEquals(one.getBirthday(), two.getBirthday());
    }
}
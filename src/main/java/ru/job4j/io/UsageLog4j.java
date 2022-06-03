package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte age = 30;
        short num = 20000;
        int price = 13_360_345;
        long id = 15_120_430_320_000L;
        double square = 40.34;
        float sale = 0.03F;
        char liter = 'A';
        boolean isEmpty = true;
        LOG.debug("Возраст : {}, номер : {}", age, num);
        LOG.info(
                "info object id: {}, price: {} rub, amortization: {}, square: {}m2, class: \"{}\", empty: {}",
                id, price, sale, square, liter, isEmpty);
    }
}

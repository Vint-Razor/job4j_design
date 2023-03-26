package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;

public class Bread extends Food {

    public Bread(String name, LocalDate expiryDate, LocalDate createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}

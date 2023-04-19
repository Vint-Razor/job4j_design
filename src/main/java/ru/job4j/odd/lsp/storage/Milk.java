package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;

public class Milk extends Food {

    public Milk(String name, LocalDate createDate, LocalDate expiryDate, double price, int discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}

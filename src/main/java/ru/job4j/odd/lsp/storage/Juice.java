package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;

public class Juice extends Food {

    public Juice(String name, LocalDate expiryDate, LocalDate createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}

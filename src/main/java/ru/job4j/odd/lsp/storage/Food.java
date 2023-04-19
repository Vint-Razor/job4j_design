package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;

public abstract class Food {
    private final String name;
    private final LocalDate expiryDate;
    private final LocalDate createDate;
    private double price;
    private int discount;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, double price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

}

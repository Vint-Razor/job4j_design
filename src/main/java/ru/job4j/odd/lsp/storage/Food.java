package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public abstract class Food {
    private final String name;
    private final LocalDate expiryDate;
    private final LocalDate createDate;
    private double price;
    private int discount;

    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price, int discount) {
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

    public int calcExpirationPer() {
        double allTerm = DAYS.between(getCreateDate(), getExpiryDate());
        double remainingTerm = DAYS.between(LocalDate.now(), getExpiryDate());
        double leftExpirationPer = 100 - (100 / (allTerm / remainingTerm));
        return remainingTerm < 0 ? 0 : (int) leftExpirationPer;
    }
}

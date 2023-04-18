package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Класс для вычисления срока годности в процентах
 *
 */
public class CalcExpiration {
    private LocalDate now;

    public CalcExpiration(LocalDate now) {
        this.now = now;
    }

    public LocalDate getNow() {
        return now;
    }

    public void setNow(LocalDate now) {
        this.now = now;
    }

    /**
     *
     * @param createDate дата производства
     * @param expiryDate срок годности
     * @return время годности в процентах (чем меньше тем свежей продукт)
     */
    public int calcPer(LocalDate createDate, LocalDate expiryDate) {
        double allTerm = DAYS.between(createDate, expiryDate);
        double remainingTerm = DAYS.between(now, expiryDate);
        double leftExpirationPer = 100 - (100 / (allTerm / remainingTerm));
        return remainingTerm < 0 ? 0 : (int) leftExpirationPer;
    }
}

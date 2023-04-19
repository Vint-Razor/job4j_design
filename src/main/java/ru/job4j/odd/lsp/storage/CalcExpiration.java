package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Класс для вычисления срока годности в процентах относительно заданной даты.
 * За дату отвечает поле now, оно задаётся в конструкторе. Изменить переменную
 * можно в методе setNow();
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
     *Метод вычисляет оставшийся срок годности в процентах относительно заданной даты
     * @param createDate дата производства
     * @param expiryDate срок годности
     * @return время годности в процентах (чем меньше число тем свежее продукт)
     * 100% - срок годности вышел
     * 0% - абсолютно новый продукт
     */
    public int calcPer(LocalDate createDate, LocalDate expiryDate) {
        double allTerm = DAYS.between(createDate, expiryDate);
        double remainingTerm = DAYS.between(now, expiryDate);
        double leftExpirationPer = 100 - (100 / (allTerm / remainingTerm));
        return remainingTerm < 0 ? 100 : (int) leftExpirationPer;
    }
}

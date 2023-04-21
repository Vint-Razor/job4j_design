package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Класс для вычисления срока годности {@link ru.job4j.odd.lsp.storage.Food} в
 * процентах относительно заданной даты.
 * За дату отвечает поле now, оно задаётся в конструкторе. Изменить переменную
 * можно в методе setNow;
 * @author Рустам
 * @version 1.0
 * 21.04.23
 * */
public class CalcExpiration {
    private LocalDate now;

    public CalcExpiration(LocalDate now) {
        this.now = now;
    }

    public LocalDate getNow() {
        return now;
    }

    /**
     * Изменяет текущую дату относительно которой вычисляется в методе calcPer срок годности
     * @param now - текущая дата
     */
    public void setNow(LocalDate now) {
        this.now = now;
    }

    /**
     *Метод вычисляет оставшийся срок годности в процентах относительно заданной даты которую
     * можно задать в методе setNow()
     * @param createDate дата производства
     * @param expiryDate срок годности
     * @return время годности в процентах (чем меньше число тем свежее продукт)
     * <br>100% - срок годности вышел
     * <br>0% - абсолютно новый продукт
     */
    public int calcPer(LocalDate createDate, LocalDate expiryDate) {
        double allTerm = DAYS.between(createDate, expiryDate);
        double remainingTerm = DAYS.between(now, expiryDate);
        double leftExpirationPer = 100 - (100 / (allTerm / remainingTerm));
        return remainingTerm < 0 ? 100 : (int) leftExpirationPer;
    }
}

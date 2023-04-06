package ru.job4j.odd.lsp.parking;

public class Car implements Auto {
    private static final int CAR_SIZE = 1;

    @Override
    public int getSize() {
        return CAR_SIZE;
    }
}

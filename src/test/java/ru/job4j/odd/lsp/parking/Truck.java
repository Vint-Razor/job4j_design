package ru.job4j.odd.lsp.parking;

public class Truck implements Auto {
    private static final int TRUCK_SIZE = 3;

    @Override
    public int getSize() {
        return TRUCK_SIZE;
    }
}

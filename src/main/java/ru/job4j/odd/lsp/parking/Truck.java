package ru.job4j.odd.lsp.parking;

public class Truck extends Auto {
    private static final int TRUCK_SIZE = 3;

    public Truck(String number) {
        super(TRUCK_SIZE, number);
    }

    @Override
    public int getSize() {
        return TRUCK_SIZE;
    }
}

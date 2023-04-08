package ru.job4j.odd.lsp.parking;

public class Truck extends Auto {
    private static final int MIN_SIZE_TRUCK = 2;

    public Truck(int size, String number) {
        super(size, number);
        validator(size, number);
    }

    private void validator(int size, String number) {
        if (size < MIN_SIZE_TRUCK) {
            throw new IllegalArgumentException(String.format(
                    "Длинна грузовика не длжна быть меньше %d", MIN_SIZE_TRUCK));
        }
        if (number.isEmpty() || number.isBlank()) {
            throw new IllegalArgumentException("Номер не может быть пустым");
        }
    }
}

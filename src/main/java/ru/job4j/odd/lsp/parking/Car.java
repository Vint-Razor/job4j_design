package ru.job4j.odd.lsp.parking;

public class Car extends Auto {
    private static final int MAX_SIZE_CAR = 1;

    public Car(int size, String number) {
        super(size, number);
        validator(size, number);
    }

    private void validator(int size, String number) {
        if (size > MAX_SIZE_CAR) {
            throw new IllegalArgumentException(String.format(
                    "превышен размер автомобиля, он не должен привышать %d", MAX_SIZE_CAR));
        }
        if (number.isEmpty() || number.isBlank()) {
            throw new IllegalArgumentException("номер автомобиля не может быть пустым");
        }
    }
}

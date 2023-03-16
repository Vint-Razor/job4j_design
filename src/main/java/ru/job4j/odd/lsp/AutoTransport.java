package ru.job4j.odd.lsp;

public class AutoTransport {
    protected float fuel;

    public AutoTransport(float fuel) {
        this.fuel = fuel;
    }

    public void move(float km) {
        if (km < 0) {
            throw new IllegalArgumentException("некорректная дистанция");
        }
        if (fuel < 0) {
            throw new IllegalArgumentException("мало топлива");
        }
    }
}

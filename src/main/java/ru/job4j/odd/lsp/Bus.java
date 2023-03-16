package ru.job4j.odd.lsp;

public class Bus extends AutoTransport {

    public Bus(float fuel) {
        super(fuel);
    }

    public void move(float km) {
        if (km < 0) {
            throw new IllegalArgumentException("некорректная дистанция");
        }
        if (fuel < 5) {
            throw new IllegalArgumentException("нужно больше топлива");
        }
    }
}

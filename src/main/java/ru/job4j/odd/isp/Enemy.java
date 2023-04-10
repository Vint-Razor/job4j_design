package ru.job4j.odd.isp;

public class Enemy implements Solder {

    @Override
    public void putLoot() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void takeLoot() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void talk() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void attack(Solder solder) {
        System.out.printf("враг атакует %s", solder);
    }
}

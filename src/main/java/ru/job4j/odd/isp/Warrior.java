package ru.job4j.odd.isp;

public class Warrior implements Unit {
    private final String name;
    private final int health;

    public Warrior(String name, int health) {
        this.name = name;
        this.health = health;
    }

    @Override
    public void attack(Unit unit) {
        System.out.format("воин атакует %s", unit);
    }

    @Override
    public void info() {
        System.out.format("Воин: %s\nЗдоровье : %s", name, health);
    }

    @Override
    public void spell(Unit unit) {
        throw new UnsupportedOperationException("воин не колдует");
    }

    @Override
    public void heal(Unit unit) {
        throw new UnsupportedOperationException("воин не лечит");
    }
}

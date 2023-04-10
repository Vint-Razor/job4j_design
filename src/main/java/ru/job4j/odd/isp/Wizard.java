package ru.job4j.odd.isp;

public class Wizard implements Unit {
    private final String name;
    private int health;
    private int mana;

    public Wizard(String name, int health, int mana) {
        this.name = name;
        this.health = health;
        this.mana = mana;
    }

    @Override
    public void attack(Unit unit) {
        System.out.format("Колдун атакует %s", unit);
    }

    @Override
    public void info() {
        System.out.format("Колдун : %s", name);
        System.out.format("здоровье : %s", health);
        System.out.format("мана : %s", mana);
    }

    @Override
    public void spell(Unit unit) {
        System.out.printf("Колдун накладывает заклинание на %s", unit);
    }

    @Override
    public void heal(Unit unit) {
        System.out.printf("Колдун лечит %s", unit);
    }
}

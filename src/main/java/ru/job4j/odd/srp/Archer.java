package ru.job4j.odd.srp;

public class Archer {

    private String name;
    private int health;
    private Weapon weapon;

    public Archer(String name, int health) {
        this.name = name;
        this.health = health;
        init();
    }

    private void init() {
        setWeapon(new Weapon("Long Bow", 2));
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}

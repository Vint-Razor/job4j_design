package ru.job4j.odd.ocp;

public class Mage implements Attacker {
    private final int damage;

    public Mage(int damage) {
        this.damage = damage;
    }

    @Override
    public void attack() {
        System.out.println("magic " + this.damage);
    }
}

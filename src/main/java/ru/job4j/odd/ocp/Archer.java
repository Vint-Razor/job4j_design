package ru.job4j.odd.ocp;

public class Archer implements Attacker {
    private final int damage;

    public Archer(int damage) {
        this.damage = damage;
    }

    @Override
    public void attack() {
        System.out.println("shoot " + this.damage);
    }
}

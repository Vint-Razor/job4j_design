package ru.job4j.odd.srp;

public class Warrior {

    private int health;
    private String name;

    public Warrior(int health, String name) {
        this.health = health;
        this.name = name;
    }

    public void attack(Warrior enemy) {
        enemy.setHealth(enemy.getHealth() - 1);
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

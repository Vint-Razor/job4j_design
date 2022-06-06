package ru.job4j.serialization.json;

import java.util.Arrays;

public class Warrior {
    private final String name;
    private final int health;
    private final boolean enemy;
    private final Weapon weapon;
    private final String[] features;

    public Warrior(String name, int health, boolean enemy, Weapon weapon, String[] features) {
        this.name = name;
        this.health = health;
        this.enemy = enemy;
        this.weapon = weapon;
        this.features = features;
    }

    @Override
    public String toString() {
        return "Warrior{"
                + "name='" + name + '\''
                + ", health=" + health
                + ", enemy=" + enemy
                + ", weapon=" + weapon
                + ", features=" + Arrays.toString(features)
                + '}';
    }
}

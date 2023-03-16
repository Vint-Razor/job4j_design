package ru.job4j.odd.lsp;

public class HeavyWarrior extends Human {
    private int armour;

    public HeavyWarrior(String name, int health, int armour) {
        super(name, health);
        this.armour = armour;
    }

    @Override
    public void setHealth(int health) {
        this.health = health + armour;
    }

    public static void main(String[] args) {
        Human warrior = new HeavyWarrior("Bill", 3, 2);
        warrior.setHealth(-4);
        System.out.println(warrior.getHealth());
    }
}

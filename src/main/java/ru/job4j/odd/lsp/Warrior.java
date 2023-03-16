package ru.job4j.odd.lsp;

public class Warrior extends Mage {

    public Warrior(String name, int health, int mana) {
        super(name, health, mana);
    }

    @Override
    public int getMana() {
        throw new IllegalArgumentException("воин не может колдовать");
    }

    public static void main(String[] args) {
        Mage warrior = new Warrior("Bob", 3, 0);
        warrior.getMana();
    }
}

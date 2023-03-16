package ru.job4j.odd.lsp;

public class Necromancer extends Mage {

    public Necromancer(String name, int health, int mana) {
        super(name, health, mana);
    }

    @Override
    public void spell() {
        if (mana < 5) {
            throw new IllegalArgumentException("заклинанию не хватает манны");
        }
    }

    public static void main(String[] args) {
        Mage mage = new Necromancer("Frodo", 3, 4);
        mage.spell();
    }
}

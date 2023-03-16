package ru.job4j.odd.lsp;

public class Human {
    protected String name;
    protected int health;

    public Human(String name, int health) {
        this.name = name;
        validate(health);
        this.health = health;
    }

    private void validate(int health) {
        if (health < 1) {
            throw new IllegalArgumentException("здоровье не может быть меньше 1");
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        validate(health);
        this.health = health;
    }
}

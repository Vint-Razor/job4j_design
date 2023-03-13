package ru.job4j.odd.ocp;


public class WarriorFactory {

    private Attacker birth(String name) {
        return switch (name) {
            case ("archer") -> new Archer(2);
            case ("mage") -> new Mage(3);
            default -> null;
        };
    }
}

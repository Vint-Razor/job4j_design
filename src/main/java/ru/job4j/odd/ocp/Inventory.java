package ru.job4j.odd.ocp;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Weapon> loots = new ArrayList<>();

    public void put(Weapon loot) {
        loots.add(loot);
    }
}

package ru.job4j.odd.isp;

public class Hero implements Solder {

    @Override
    public void putLoot() {
        System.out.println("герой положил предмет");
    }

    @Override
    public void takeLoot() {
        System.out.println("герой взял предмет");
    }

    @Override
    public void talk() {
        System.out.println("герой говорит");
    }

    @Override
    public void attack(Solder solder) {
        System.out.printf("герой атакует %s", solder);
    }
}

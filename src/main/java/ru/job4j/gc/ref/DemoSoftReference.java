package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DemoSoftReference {
    private static final int MAX_PIN = 10000;
    private static final int NUM_PINS_IN_LIST = 100;
    private final Random random;
    private final List<SoftReference<Integer>> pins = new ArrayList<>();

    public DemoSoftReference(Random random) {
        this.random = random;
        fillPinsList();
    }

    public int pinGenerator() {
        return random.nextInt(MAX_PIN);
    }

    private void fillPinsList() {
        for (int i = 0; i < NUM_PINS_IN_LIST; i++) {
            pins.add(new SoftReference<>(pinGenerator()));
        }
    }

    public int getPin() {
        Integer pin = pins.get(random.nextInt(NUM_PINS_IN_LIST)).get();
        if (pin == null) {
            pin = pinGenerator();
        }
        return pin;
    }

    public static void main(String[] args) {
        final Random random = new Random();
        final DemoSoftReference demoSoftReference = new DemoSoftReference(random);
        System.out.println(demoSoftReference.getPin());
    }
}

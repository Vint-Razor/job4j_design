package ru.job4j.odd.isp;

public class Cat implements Animal {

    @Override
    public void run() {
        System.out.println("the cat is running");
    }

    @Override
    public void voice() {
        System.out.println("Meow!");
    }

    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }
}

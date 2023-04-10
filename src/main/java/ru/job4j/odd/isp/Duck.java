package ru.job4j.odd.isp;

public class Duck implements Animal {

    @Override
    public void run() {
        System.out.println("the duck is running");
    }

    @Override
    public void voice() {
        System.out.println("quack");
    }

    @Override
    public void fly() {
        System.out.println("the duck is flying");
    }
}

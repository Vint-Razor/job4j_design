package ru.job4j.gc;

public class Person {
    private int age;
    private String name;

    public Person(int arg, String name) {
        this.age = arg;
        this.name = name;
    }

    @Override
    protected void finalize() {
        System.out.printf("Removed %d %s%n", age, name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

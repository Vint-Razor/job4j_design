package ru.job4j.odd.dip.lesson;

public class ZooApp {

    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        Animal wolf = new Wolf("Wolfy", 2);
        zoo.addAnimal(wolf);
        System.out.println(zoo.getAnimalInfo("Wolfy").get());
        Employee jack = new Zookeeper(1, "Jack", 100);
        Employee.addEmployee(jack);
    }
}

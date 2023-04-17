package ru.job4j.odd.dip.lesson;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Zoo {
    private List<Animal> animalList = new LinkedList<>();

    public boolean addAnimal(Animal animal) {
        return animalList.add(animal);
    }

    public boolean deleteAnimal(Animal animal) {
        return animalList.remove(animal);
    }

    private Optional<Animal> findAnimal(Animal animal) {
        Optional<Animal> rsl = Optional.empty();
        for (Animal anim : animalList) {
            if (anim.equals(animal)) {
                rsl = Optional.of(anim);
                break;
            }
        }
        if (rsl.isEmpty()) {
            System.out.printf("Животное с именем %s не найдено", animal);
        }
        return rsl;
    }

    public Optional<AnimalInfo> getAnimalInfo(Animal animal) {
        final Optional<Animal> optional = findAnimal(animal);
        return Optional.of(new AnimalInfo(optional.get().getName(), optional.get().getAge()));
    }

    static class AnimalInfo {
        private final String name;
        private final int age;

        public AnimalInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "AnimalInfo{"
                    + "name='" + name + '\''
                    + ", age=" + age
                    + '}';
        }
    }
}

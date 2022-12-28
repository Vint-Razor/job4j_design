package ru.job4j.gc.leak;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {
    private static final String PATH_NAME = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    private static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    private static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    private static final String SEPARATOR = "";
    private static final int NEW_USER = 1000;

    private List<String> names;
    private List<String> surnames;
    private List<String> patrons;
    private final List<User> users = new ArrayList<>();
    private final Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < NEW_USER; i++) {
            builder.append(surnames.get(random.nextInt(surnames.size())));
            builder.append(SEPARATOR);
            builder.append(names.get(random.nextInt(names.size())));
            builder.append(SEPARATOR);
            builder.append(patrons.get(random.nextInt(patrons.size())));
            users.add(new User(builder.toString()));
            builder.setLength(0);
        }
    }

    private void readAll() {
        names = read(PATH_NAME);
        surnames = read(PATH_SURNAMES);
        patrons = read(PATH_PATRONS);
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }

    public List<User> getUsers() {
        return users;
    }

}

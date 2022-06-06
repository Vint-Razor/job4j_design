package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Warrior warrior = new Warrior("Jack", 20, false,
                new Weapon("Sword", 3), new String[]{"berserk", "slow"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(warrior));
        final String warriorJson =
                "{"
                        + "\"health\":15,"
                        + "\"name\":\"Bob\","
                        + "\"enemy\":true,"
                        + "\"weapon\":"
                        + "{"
                        + "\"name\":\"bow\","
                        + "\"damage\":2"
                        + "},"
                        + "\"features\":"
                        + "[\"archer\", \"loser\"]"
                        + "}";
        final Warrior warriorMod = gson.fromJson(warriorJson, Warrior.class);
        System.out.println(warriorMod);
    }
}

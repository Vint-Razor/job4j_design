package ru.job4j.serialization.json;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject(
                "{"
                        + "\"name\":\"Jack\","
                        + "\"health\":20,"
                        + "\"enemy\":false,"
                        + "\"weapon\":"
                         + "{"
                            + "\"name\":\"sword\","
                            + "\"damage\":3"
                         + "},"
                        + "\"features\":[\"berserk\",\"slow\"]"
                        + "}"
        );
        System.out.println(jsonObject);
        Warrior warrior = new Warrior("Jack", 20, false, new Weapon("sword", 3),
                new String[] {"berserk", "slow"});
        String json = new JSONObject(warrior).toString();
        System.out.println(json);
    }
}

package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "weapon")
public class Weapon {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private int damage;

    public Weapon() {
    }

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "Weapon{"
                + "name='" + name + '\''
                + ", damage=" + damage
                + '}';
    }
}

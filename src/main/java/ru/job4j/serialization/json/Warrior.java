package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "warrior")
@XmlAccessorType(XmlAccessType.FIELD)
public class Warrior {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private int health;
    @XmlAttribute
    private boolean enemy;
    private Weapon weapon;
    @XmlElementWrapper(name = "features")
    @XmlElement(name = "feature")
    private String[] features;

    public Warrior() {
    }

    public Warrior(String name, int health, boolean enemy, Weapon weapon, String[] features) {
        this.name = name;
        this.health = health;
        this.enemy = enemy;
        this.weapon = weapon;
        this.features = features;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public String[] getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return "Warrior{"
                + "name='" + name + '\''
                + ", health=" + health
                + ", enemy=" + enemy
                + ", weapon=" + weapon
                + ", features=" + Arrays.toString(features)
                + '}';
    }
}

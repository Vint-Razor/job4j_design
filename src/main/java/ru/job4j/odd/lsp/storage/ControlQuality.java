package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

public class ControlQuality {
    private final Map<String, Store> storeMap;

    public ControlQuality(Map<String, Store> storeMap) {
        this.storeMap = storeMap;
    }

    public void checkFood(Food food) {
        LocalDate now = LocalDate.now();
        if (food.getExpiryDate().isBefore(now)) {
            storeMap.get("Trash").addFoodList(food);
        }
        int expirationPer = calcExpirationPer(food);
        if (expirationPer <= 75 && expirationPer >= 25) {
            storeMap.get("Shop").addFoodList(food);
        }
        if (expirationPer > 75) {
            double price = food.getPrice();
            double newPrice = price - (price * food.getDiscount() / 100);
            food.setPrice(newPrice);
            storeMap.get("Shop").addFoodList(food);
        }
        if (expirationPer < 25) {
            storeMap.get("Warehouse").addFoodList(food);
        }
    }

    private int calcExpirationPer(Food food) {
        double allTerm = DAYS.between(food.getCreateDate(), food.getExpiryDate());
        double remainingTerm = DAYS.between(LocalDate.now(), food.getExpiryDate());
        double leftExpirationPer = 100 - (100 / (allTerm / remainingTerm));
        return (int) leftExpirationPer;
    }
}

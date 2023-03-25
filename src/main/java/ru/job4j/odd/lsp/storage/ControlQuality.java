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
        if (calcExpirationPer(food) <= 75 && calcExpirationPer(food) >= 25) {
            storeMap.get("Shop").addFoodList(food);
        }
        if (calcExpirationPer(food) > 75) {
            double price = food.getPrice();
            double newPrice = price - (price * food.getDiscount() / 100);
            food.setPrice(newPrice);
            storeMap.get("Shop").addFoodList(food);
        }
    }

    private int calcExpirationPer(Food food) {
        double allTerm = DAYS.between(food.getCreateDate(), food.getExpiryDate());
        double remainingTerm = DAYS.between(LocalDate.now(), food.getExpiryDate());
        double leftExpirationPer = 100 - (100 / (allTerm / remainingTerm));
        return (int) leftExpirationPer;
    }
}

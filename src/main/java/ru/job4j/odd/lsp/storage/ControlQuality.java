package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class ControlQuality {
    private final List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public void checkFood(Food food) {
        LocalDate now = LocalDate.now();
        if (food.getExpiryDate().isBefore(now)) {
            storeList.get(0).addFoodList(food);
        }
        int expirationPer = calcExpirationPer(food);
        if (expirationPer <= 75 && expirationPer >= 25) {
            storeList.get(1).addFoodList(food);
        }
        if (expirationPer > 75) {
            double price = food.getPrice();
            double newPrice = price - (price * food.getDiscount() / 100);
            food.setPrice(newPrice);
            storeList.get(1).addFoodList(food);
        }
        if (expirationPer < 25) {
            storeList.get(2).addFoodList(food);
        }
    }

    private int calcExpirationPer(Food food) {
        double allTerm = DAYS.between(food.getCreateDate(), food.getExpiryDate());
        double remainingTerm = DAYS.between(LocalDate.now(), food.getExpiryDate());
        double leftExpirationPer = 100 - (100 / (allTerm / remainingTerm));
        return (int) leftExpirationPer;
    }
}

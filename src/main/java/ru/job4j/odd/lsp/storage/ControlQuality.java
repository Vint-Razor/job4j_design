package ru.job4j.odd.lsp.storage;

import java.util.List;

public class ControlQuality {
    private final List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    public void checkFood(Food food) {
        for (Store store : storeList) {
            if (store.getCondition().test(food)) {
                store.addFood(food);
            }

            if (store.getDiscountCondition().isPresent()
                    && store.getDiscountCondition().get().test(food)) {
                double price = food.getPrice();
                food.setPrice(price - (price * food.getDiscount() / 100));
                store.addFood(food);
            }
        }
    }
}

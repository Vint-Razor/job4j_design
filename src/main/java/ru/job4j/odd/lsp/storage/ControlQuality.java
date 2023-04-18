package ru.job4j.odd.lsp.storage;

import java.util.ArrayList;
import java.util.Collections;
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

    public void resort() {
        List<Food> tempFodList = new ArrayList<>();
        for (Store store : storeList) {
            List<Food> foodList = store.getFoodList();
            // 1. сохранить старую еду
            Collections.copy(tempFodList, store.getFoodList());
            // 2. удалить старую еду из storeList;
            // 3. checkFood сохраненную еду
        }
    }
}

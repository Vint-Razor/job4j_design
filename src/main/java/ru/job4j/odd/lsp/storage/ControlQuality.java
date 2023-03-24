package ru.job4j.odd.lsp.storage;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {
    private final List<Store> listStores;

    public ControlQuality(List<Store> storeList) {
        this.listStores = storeList;
    }

    public void checkFood(Food food) {
        LocalDate now = LocalDate.now();
        if (food.getExpiryDate().isBefore(now)) {
            for (Store store : listStores) {
                if ("Trash".equals(store.getName())) {
                    store.addFoodList(food);
                }
            }
        }
    }
}

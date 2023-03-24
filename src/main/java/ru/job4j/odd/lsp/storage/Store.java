package ru.job4j.odd.lsp.storage;

import java.util.List;

public interface Store {

    List<Food> getFoodList();

    void addFoodList(Food food);

    String getName();
}

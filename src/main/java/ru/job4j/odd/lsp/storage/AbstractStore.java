package ru.job4j.odd.lsp.storage;


import java.util.LinkedList;
import java.util.List;

public abstract class AbstractStore implements Store {

    private List<Food> foodList = new LinkedList<>();

    @Override
    public List<Food> getFoodList() {
        return foodList;
    }

    @Override
    public void addFoodList(Food food) {
        foodList.add(food);
    }
}

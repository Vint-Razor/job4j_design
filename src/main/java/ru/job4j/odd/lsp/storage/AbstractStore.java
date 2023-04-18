package ru.job4j.odd.lsp.storage;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractStore implements Store {
    CalcExpiration calc;
    private List<Food> foodList = new LinkedList<>();

    public AbstractStore(CalcExpiration calc) {
        this.calc = calc;
    }

    @Override
    public List<Food> getFoodList() {
        return foodList;
    }

    @Override
    public void addFood(Food food) {
        foodList.add(food);
    }
}

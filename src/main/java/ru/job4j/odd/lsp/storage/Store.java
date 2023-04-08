package ru.job4j.odd.lsp.storage;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Store {

    List<Food> getFoodList();

    void addFood(Food food);

    Predicate<Food> getCondition();

    Optional<Predicate<Food>> getDiscountCondition();
}

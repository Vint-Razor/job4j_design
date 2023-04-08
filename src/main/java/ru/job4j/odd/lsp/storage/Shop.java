package ru.job4j.odd.lsp.storage;

import java.util.Optional;
import java.util.function.Predicate;

public class Shop extends AbstractStore {

    @Override
    public Predicate<Food> getCondition() {
        return a -> a.calcExpirationPer() <= 75 && a.calcExpirationPer() >= 25;
    }

    @Override
    public Optional<Predicate<Food>> getDiscountCondition() {
        return Optional.of(a -> a.calcExpirationPer() > 75);
    }
}

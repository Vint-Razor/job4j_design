package ru.job4j.odd.lsp.storage;

import java.util.Optional;
import java.util.function.Predicate;

public class Warehouse extends AbstractStore {

    @Override
    public Predicate<Food> getCondition() {
        return a -> a.calcExpirationPer() < 25 && a.calcExpirationPer() > 0;
    }

    @Override
    public Optional<Predicate<Food>> getDiscountCondition() {
        return Optional.empty();
    }
}

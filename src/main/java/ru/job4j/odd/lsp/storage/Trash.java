package ru.job4j.odd.lsp.storage;

import java.util.Optional;
import java.util.function.Predicate;

public class Trash extends AbstractStore {

    @Override
    public Predicate<Food> getCondition() {
        return a -> a.calcExpirationPer() == 0;
    }

    @Override
    public Optional<Predicate<Food>> getDiscountCondition() {
        return Optional.empty();
    }
}

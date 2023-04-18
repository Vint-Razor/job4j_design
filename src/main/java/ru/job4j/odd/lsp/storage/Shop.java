package ru.job4j.odd.lsp.storage;

import java.util.Optional;
import java.util.function.Predicate;

public class Shop extends AbstractStore {

    public Shop(CalcExpiration calc) {
        super(calc);
    }

    @Override
    public Predicate<Food> getCondition() {
        return a -> calc.calcPer(a.getCreateDate(), a.getExpiryDate()) <= 75
                && calc.calcPer(a.getCreateDate(), a.getExpiryDate()) >= 25;
    }

    @Override
    public Optional<Predicate<Food>> getDiscountCondition() {
        return Optional.of(a -> calc.calcPer(a.getCreateDate(), a.getExpiryDate()) > 75);
    }
}

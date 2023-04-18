package ru.job4j.odd.lsp.storage;

import java.util.Optional;
import java.util.function.Predicate;

public class Warehouse extends AbstractStore {

    public Warehouse(CalcExpiration calc) {
        super(calc);
    }

    @Override
    public Predicate<Food> getCondition() {
        return a -> calc.calcPer(a.getCreateDate(), a.getExpiryDate()) < 25
                && calc.calcPer(a.getCreateDate(), a.getExpiryDate()) > 0;
    }

    @Override
    public Optional<Predicate<Food>> getDiscountCondition() {
        return Optional.empty();
    }
}

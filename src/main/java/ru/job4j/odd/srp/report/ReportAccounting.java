package ru.job4j.odd.srp.report;

import ru.job4j.odd.srp.currency.Currency;
import ru.job4j.odd.srp.currency.CurrencyConverter;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.Store;


import java.util.function.Predicate;

public class ReportAccounting implements Report {
    private Store store;
    private Currency source;
    private Currency target;
    private CurrencyConverter converter;

    public ReportAccounting(Store store, CurrencyConverter converter, Currency source, Currency target) {
        this.store = store;
        this.source = source;
        this.target = target;
        this.converter = converter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return null;
    }
}

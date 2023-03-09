package ru.job4j.odd.srp.currency;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}

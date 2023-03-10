package ru.job4j.odd.srp.report;

import ru.job4j.odd.srp.formatter.DateTimeParser;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportProgrammer implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> parser;

    public ReportProgrammer(Store store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.parser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return null;
    }
}

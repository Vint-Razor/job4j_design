package ru.job4j.odd.srp.report;

import ru.job4j.odd.srp.currency.Currency;
import ru.job4j.odd.srp.currency.CurrencyConverter;
import ru.job4j.odd.srp.formatter.DateTimeParser;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.Store;


import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportAccounting implements Report {
    private final Store store;
    private final Currency source;
    private final Currency target;
    private final CurrencyConverter converter;
    DateTimeParser<Calendar> dateTimeParser;

    public ReportAccounting(Store store, DateTimeParser<Calendar> parser,
            CurrencyConverter converter, Currency source, Currency target) {
        this.store = store;
        this.source = source;
        this.target = target;
        this.converter = converter;
        this.dateTimeParser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        StringBuilder sb = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee emp : employees) {
            double salaryConvert = converter.convert(source, emp.getSalary(), target);
                    sb.append(emp.getName()).append(" ")
                    .append(dateTimeParser.parse(emp.getHired())).append(" ")
                    .append(dateTimeParser.parse(emp.getFired())).append(" ")
                    .append(salaryConvert)
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}

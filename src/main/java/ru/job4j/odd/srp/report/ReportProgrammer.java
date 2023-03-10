package ru.job4j.odd.srp.report;

import ru.job4j.odd.srp.formatter.DateTimeParser;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportProgrammer implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportProgrammer(Store store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.dateTimeParser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        StringBuilder sb = new StringBuilder()
                .append("Name;Hired;Fired;Salary")
                .append(System.lineSeparator());
        for (Employee emp : employees) {
            sb.append(emp.getName()).append(";")
                    .append(dateTimeParser.parse(emp.getHired())).append(";")
                    .append(dateTimeParser.parse(emp.getFired())).append(";")
                    .append(emp.getSalary())
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}

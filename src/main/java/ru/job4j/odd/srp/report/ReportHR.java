package ru.job4j.odd.srp.report;

import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportHR implements Report {
    private final Store store;
    private final Comparator<Employee> comparator;

    public ReportHR(Store store, Comparator<Employee> comparator) {
        this.store = store;
        this.comparator = comparator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        employees.sort(comparator);
        StringBuilder sb = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee emp : employees) {
            sb.append(emp.getName()).append(" ")
                    .append(emp.getSalary())
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}

package ru.job4j.odd.srp.report;

import ru.job4j.odd.srp.model.Employee;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}

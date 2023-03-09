package ru.job4j.odd.srp.store;

import ru.job4j.odd.srp.model.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    void add(Employee em);

    List<Employee> findBy(Predicate<Employee> filter);
}

package ru.job4j.odd.srp.comparator;

import ru.job4j.odd.srp.model.Employee;

import java.util.Comparator;

public class CompareBySalary implements Comparator<Employee> {

    @Override
    public int compare(Employee em1, Employee em2) {
        return Double.compare(em1.getSalary(), em2.getSalary());
    }
}

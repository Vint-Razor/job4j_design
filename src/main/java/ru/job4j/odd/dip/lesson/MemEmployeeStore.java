package ru.job4j.odd.dip.lesson;

import java.util.ArrayList;
import java.util.List;

public class MemEmployeeStore implements EmployeeStore {
    private static List<Employee> employees = new ArrayList<>();

    @Override
    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    @Override
    public boolean deleteEmp(Employee employee) {
        return employees.remove(employee);
    }
}

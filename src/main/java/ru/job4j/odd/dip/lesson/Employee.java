package ru.job4j.odd.dip.lesson;

import java.util.ArrayList;
import java.util.List;

public abstract class Employee {
    private final int id;
    private final String name;
    private int salary;
    private static List<Employee> employees = new ArrayList<>();

    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public static boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    public static boolean deleteEmp(Employee employee) {
        return employees.remove(employee);
    }
}

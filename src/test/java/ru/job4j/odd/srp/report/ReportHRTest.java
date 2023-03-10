package ru.job4j.odd.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.odd.srp.comparator.CompareBySalary;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.MemStore;
import ru.job4j.odd.srp.store.Store;

import java.util.Calendar;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class ReportHRTest {

    @Test
    void whenReportHRGenerate() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee jim = new Employee("Jim", now, now, 1000);
        Employee bill = new Employee("Bill", now, now, 500);
        store.add(jim);
        store.add(bill);
        Comparator<Employee> comparator = new CompareBySalary();
        Report report = new ReportHR(store, comparator);
        String expected = report.generate(rm -> true);
        StringBuilder actual = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(jim.getName()).append(" ")
                .append(jim.getSalary())
                .append(System.lineSeparator())
                .append(bill.getName()).append(" ")
                .append(bill.getSalary())
                .append(System.lineSeparator());
        assertThat(expected).isEqualTo(actual.toString());
    }
}
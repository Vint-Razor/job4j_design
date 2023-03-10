package ru.job4j.odd.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.odd.srp.formatter.DateTimeParser;
import ru.job4j.odd.srp.formatter.ReportDateTimeParser;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.MemStore;
import ru.job4j.odd.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportProgrammerTest {

    @Test
    void whenReportProgrammerGenerate() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee jim = new Employee("Jim", now, now, 1000);
        store.add(jim);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report programmer = new ReportProgrammer(store, parser);
        String expected = programmer.generate(emp -> true);
        StringBuilder actual = new StringBuilder()
                .append("Name;Hired;Fired;Salary")
                .append(System.lineSeparator())
                .append(jim.getName()).append(";")
                .append(parser.parse(jim.getHired())).append(";")
                .append(parser.parse(jim.getFired())).append(";")
                .append(jim.getSalary())
                .append(System.lineSeparator());
        assertThat(expected).isEqualTo(actual.toString());
    }
}
package ru.job4j.odd.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.odd.srp.formatter.DateTimeParser;
import ru.job4j.odd.srp.formatter.ReportDateTimeParser;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.MemStore;
import ru.job4j.odd.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportJSONTest {

    @Test
    void checkJSONGenerate() {
        Store memStore = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee bill = new Employee("Bill", now, now, 1000);
        memStore.add(bill);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report jsonRepo = new ReportJSON(memStore, parser);
        String expected = jsonRepo.generate(emp -> true);
        StringBuilder actual = new StringBuilder()
                .append("[\"{\\\"name\\\":\\\"").append(bill.getName()).append("\\\",")
                .append("\\\"hired\\\":\\\"").append(parser.parse(bill.getHired())).append("\\\",")
                .append("\\\"fired\\\":\\\"").append(parser.parse(bill.getFired())).append("\\\",")
                .append("\\\"salary\\\":").append(bill.getSalary())
                .append("}\"]");
        assertThat(actual.toString()).isEqualTo(expected);
    }
}
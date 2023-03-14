package ru.job4j.odd.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.odd.srp.formatter.DateTimeParser;
import ru.job4j.odd.srp.formatter.ReportDateTimeParser;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.MemStore;
import ru.job4j.odd.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportXMLTest {

    @Test
    void checkXMLGenerate() {
        Store memStore = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee john = new Employee("John", now, now, 500);
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        memStore.add(john);
        Report reportXML = new ReportXML(memStore, dateTimeParser);
        String actual = reportXML.generate(emp -> true);
        StringBuilder expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append(System.lineSeparator())
                .append("<employee name=\"John\" salary=\"500.0\">").append(System.lineSeparator())
                        .append("<hired>2023-03-14T20:59:48.114+05:00</hired>").append(System.lineSeparator())
                        .append("<fired>2023-03-14T20:59:48.114+05:00</fired>").append(System.lineSeparator())
                        .append("</employee>").append(System.lineSeparator());
        assertThat(expected.toString()).isEqualTo(actual);
    }
}
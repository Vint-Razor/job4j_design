package ru.job4j.odd.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.odd.srp.formatter.DateTimeParser;
import ru.job4j.odd.srp.formatter.ReportDateTimeParser;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.MemStore;
import ru.job4j.odd.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportXMLTest {

    @Test
    void checkXMLGenerate() {
        Store memStore = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee john = new Employee("John", now, now, 500);
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        memStore.add(john);
        Report reportXML = new ReportXML(memStore);
        String expect = reportXML.generate(emp -> true);
        String actual = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employee name=\"John\" salary=\"500.0\">\n"
                + "    <hired>" + dateTimeParser.parse(john.getHired()) + "</hired>\n"
                + "    <fired>" + dateTimeParser.parse(john.getFired()) + "</fired>\n"
                + "</employee>\n";
        assertThat(actual).isEqualTo(expect);
    }
}
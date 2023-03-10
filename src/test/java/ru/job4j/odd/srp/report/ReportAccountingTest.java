package ru.job4j.odd.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.odd.srp.currency.Currency;
import ru.job4j.odd.srp.currency.CurrencyConverter;
import ru.job4j.odd.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.odd.srp.formatter.DateTimeParser;
import ru.job4j.odd.srp.formatter.ReportDateTimeParser;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.MemStore;
import ru.job4j.odd.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportAccountingTest {

    @Test
    void whenAccountingReportGenerate() {
        Store memStore = new MemStore();
        Calendar now = Calendar.getInstance();
        double rubToUSD = 65D;
        Employee bill = new Employee("Bill", now, now, 1000);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        memStore.add(bill);
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Report report = new ReportAccounting(memStore, parser, converter, Currency.USD, Currency.RUB);
        String expected = report.generate(em -> true);
        StringBuilder actual = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(bill.getName()).append(" ")
                .append(parser.parse(bill.getHired())).append(" ")
                .append(parser.parse(bill.getFired())).append(" ")
                .append(bill.getSalary() * rubToUSD)
                .append(System.lineSeparator());
        assertThat(expected).isEqualTo(actual.toString());
    }
}
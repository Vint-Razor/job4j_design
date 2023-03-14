package ru.job4j.odd.srp.report;

import ru.job4j.odd.srp.formatter.DateTimeParser;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportXML implements Report {
    private final Store memStore;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportXML(Store memStore, DateTimeParser<Calendar> dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
        this.memStore = memStore;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder sb = new StringBuilder();
        for (Employee emp : memStore.findBy(filter)) {
            try {
                JAXBContext context = JAXBContext.newInstance(Employee.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                try (StringWriter writer = new StringWriter()) {
                    marshaller.marshal(emp, writer);
                    sb.append(writer.getBuffer());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}

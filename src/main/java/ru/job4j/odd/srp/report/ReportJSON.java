package ru.job4j.odd.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import ru.job4j.odd.srp.formatter.DateTimeParser;
import ru.job4j.odd.srp.formatter.JSONDateAdapter;
import ru.job4j.odd.srp.model.Employee;
import ru.job4j.odd.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Predicate;

public class ReportJSON implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> parser;

    public ReportJSON(Store store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.parser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Calendar.class, new JSONDateAdapter());
        builder.registerTypeAdapter(GregorianCalendar.class, new JSONDateAdapter());
        Gson gson = builder.create();
        JSONArray jsonArray = new JSONArray();
        for (Employee emp : store.findBy(filter)) {
            jsonArray.put(gson.toJson(emp));
        }
        return jsonArray.toString();
    }
}

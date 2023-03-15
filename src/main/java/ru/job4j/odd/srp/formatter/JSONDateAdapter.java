package ru.job4j.odd.srp.formatter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JSONDateAdapter implements JsonSerializer<Calendar>, JsonDeserializer<Calendar> {
    private static final DateFormat DATE_FORMAT
            = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public Calendar deserialize(JsonElement jsonElement, Type type,
                                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(DATE_FORMAT.parse(jsonElement.getAsJsonPrimitive().getAsString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    @Override
    public JsonElement serialize(Calendar calendar, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(DATE_FORMAT.format(calendar.getTime()));
    }
}

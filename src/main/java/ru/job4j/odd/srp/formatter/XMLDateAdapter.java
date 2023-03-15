package ru.job4j.odd.srp.formatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class XMLDateAdapter extends XmlAdapter<String, Calendar> {
    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public Calendar unmarshal(String str) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(DATE_FORMAT.parse(str));
        return cal;
    }

    @Override
    public String marshal(Calendar calendar) {
        return DATE_FORMAT.format(calendar.getTime());
    }
}

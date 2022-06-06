package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {
        Path path = Path.of("./data/serialization/warrior.xml");
        Warrior warrior;
        String xml = Files.readString(path);
        JAXBContext context = JAXBContext.newInstance(Warrior.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        System.out.format("десериализация из файла %s%n", path.toAbsolutePath());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            warrior = (Warrior) unmarshaller.unmarshal(reader);
            System.out.println(warrior);
        }
        System.out.format("сереализация из обекта %s%n", warrior.getClass());
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(warrior, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
    }
}

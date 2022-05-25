package ru.job4j.io;

import jdk.jshell.spi.ExecutionControlProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class CSVReaderTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void whenFilterTwoColumns() throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "Wiliam;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.scv");
        File target = temporaryFolder.newFile("target.scv");
        ArgsName argsName = ArgsName.of(new String[] {
                "-path=" + file.getAbsoluteFile(), "-delimiter=;", "-out=" + target.getAbsoluteFile(),
                "-filter=name,age"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        assertEquals(expected, Files.readString(target.toPath()));
    }
}
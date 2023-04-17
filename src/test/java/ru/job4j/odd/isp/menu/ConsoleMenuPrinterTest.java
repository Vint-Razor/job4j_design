package ru.job4j.odd.isp.menu;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleMenuPrinterTest {
    private static final ActionDelegate ACTION_STUB = () -> System.out.println("Some action");
    private  ByteArrayOutputStream output;
    private PrintStream old;

    @BeforeEach
    void before() {
        old = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @After
    public void after() {
        System.setOut(old);
    }

    @Test
    void checkPrint() {
        Menu menu = new SimpleMenu();
        ConsoleMenuPrinter printer = new ConsoleMenuPrinter();
        menu.add(Menu.ROOT, "Papa", ACTION_STUB);
        menu.add("Papa", "Son", ACTION_STUB);
        printer.print(menu);
        StringBuilder sb = new StringBuilder();
        sb.append("1.Papa");
        sb.append(System.lineSeparator());
        sb.append("----");
        sb.append("1.1.Son");
        sb.append(System.lineSeparator());
        String actual = sb.toString();
        assertThat(actual).isEqualTo(output.toString());
    }
}
package ru.job4j.odd.isp.menu;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleMenuTest {
    private static final ActionDelegate STUB_ACTION = System.out::println;
    private ByteArrayOutputStream output;
    private PrintStream old;

    @BeforeEach
    void before() {
        old = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void after() {
        System.setOut(old);
    }

    @Test
    void checkSelect() {
        Menu menu = new SimpleMenu();
        ActionDelegate delegate = () -> System.out.print("Делигат вызван");
        menu.add(Menu.ROOT, "Сходить в магазин", delegate);
        menu.select("Сходить в магазин");
        String actual = "Делигат вызван";
        String expected = output.toString();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void iterator() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        StringBuilder sb = new StringBuilder();
        menu.forEach(i -> sb.append(i.getName()).append(" "));
        String actual = "Сходить в магазин Покормить собаку ";
        assertThat(sb.toString()).isEqualTo(actual);
    }

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }
}
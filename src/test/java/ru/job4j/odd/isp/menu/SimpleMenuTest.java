package ru.job4j.odd.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuTest {

    private static final ActionDelegate STAB_ACTION = System.out::println;

    @Test
    void checkAdd() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STAB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STAB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STAB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STAB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STAB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STAB_ACTION, "1"))
                .isEqualTo(menu.select("Сходить в магазин").get());
    }

    @Test
    void select() {
    }

    @Test
    void iterator() {
    }

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STAB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STAB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STAB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STAB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STAB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STAB_ACTION, "1"))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo("Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STAB_ACTION, "1.1"))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo("Покормить собаку",
                List.of(), STAB_ACTION, "2"))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }
}
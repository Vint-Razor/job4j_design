package ru.job4j.odd.isp.menu;

public class ConsoleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }
}

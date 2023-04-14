package ru.job4j.odd.isp.menu;

import java.util.Scanner;

public interface ActionMenu {

    boolean execute(Menu menu, Scanner scanner, MenuPrinter printer);

    String getName();
}

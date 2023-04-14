package ru.job4j.odd.isp.menu;

import java.util.List;
import java.util.Scanner;

/**
 * Этот класс будет представлять собой консольное приложение, которое позволяет:
 * - Добавить элемент в корень меню;
 * - Добавить элемент к родительскому элементу;
 * - Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action")
 * и указывать при добавлении элемента в меню);
 * - Вывести меню в консоль.
 *
 */
public class TodoApp {
    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

    public static void main(String[] args) {
        List<ActionMenu> actionMenus = List.of(
                new ActionAdd(),
                new ActionAddChild(),
                new ActionPrint(),
                new ActionExit()
        );
        Menu menu = new SimpleMenu();
        ConsoleMenuPrinter printer = new ConsoleMenuPrinter();
        Scanner scanner = new Scanner(System.in);
        Boolean exit = false;
        while (!exit) {
            showMenu(actionMenus);
            int number = Integer.parseInt(scanner.nextLine());
            exit = actionMenus.get(number - 1).execute(menu, scanner, printer);
        }
        System.out.println("Выход");
    }

    private static void showMenu(List<ActionMenu> list) {
        int count = 1;
        System.out.println("*** МЕНЮ ***");
        for (ActionMenu menu : list) {
            System.out.println(count + " " + menu.getName());
            count++;
        }
    }

    private static class ActionAdd implements ActionMenu {

        @Override
        public boolean execute(Menu menu, Scanner scanner, MenuPrinter printer) {
            System.out.println("Введите название элемента");
            String name = scanner.nextLine();
            menu.add(Menu.ROOT, name, DEFAULT_ACTION);
            return false;
        }

        @Override
        public String getName() {
            return "Добавить элемент в корень меню";
        }
    }

    private static class ActionAddChild implements ActionMenu {

        @Override
        public boolean execute(Menu menu, Scanner scanner, MenuPrinter printer) {
            System.out.println("Введите название родительского элемента");
            String parent = scanner.nextLine();
            System.out.println("Введите название элемента");
            String child = scanner.nextLine();
            if (!menu.add(parent, child, DEFAULT_ACTION)) {
                System.out.println("родительский элемент не найден");
            }
            return false;
        }

        @Override
        public String getName() {
            return "Добавить элемент к родительскому элементу;";
        }
    }

    private static class ActionPrint implements ActionMenu {

        @Override
        public boolean execute(Menu menu, Scanner scanner, MenuPrinter printer) {
            printer.print(menu);
            return false;
        }

        @Override
        public String getName() {
            return "Вывести меню в консоль";
        }
    }

    private static class ActionExit implements ActionMenu {

        @Override
        public boolean execute(Menu menu, Scanner scanner, MenuPrinter printer) {
            return true;
        }

        @Override
        public String getName() {
            return "Выход";
        }
    }
}

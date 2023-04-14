package ru.job4j.odd.isp.menu;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Этот класс будет представлять собой консольное приложение, которое позволяет:
 * - Добавить элемент в корень меню;
 * - Добавить элемент к родительскому элементу;
 * - Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action")
 * и указывать при добавлении элемента в меню);
 * - Вывести меню в консоль.
 */
public class TodoApp {
    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

    public static void main(String[] args) {
        List<ActionMenu> actionMenus = List.of(
                new ActionAdd(),
                new ActionAddChild(),
                new ActionPrint(),
                new ActionExecute(),
                new ActionExit()
        );
        Menu menu = new SimpleMenu();
        ConsoleMenuPrinter printer = new ConsoleMenuPrinter();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            showMenu(actionMenus);
            int number = Integer.parseInt(scanner.nextLine());
            if (validator(number, actionMenus)) {
                exit = actionMenus.get(number - 1).execute(menu, scanner, printer);
            } else {
                System.out.printf("Ведите число от 1 до %d\n", actionMenus.size());
            }

        }
        System.out.println("Выход");
    }

    private static boolean validator(int number, List<ActionMenu> actionMenus) {
        return number >= 1 && number <= actionMenus.size();
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

    private static class ActionExecute implements ActionMenu {

        @Override
        public boolean execute(Menu menu, Scanner scanner, MenuPrinter printer) {
            System.out.println("Укажите имя строки, которую вы хотите выполнить");
            String name = scanner.nextLine();
            Optional<Menu.MenuItemInfo> select = menu.select(name);
            if (select.isPresent()) {
                select.get().getActionDelegate().delegate();
            } else {
                System.out.printf("строка с именем %s не найдена", name);
            }
            return false;
        }

        @Override
        public String getName() {
            return "Вызвать действие";
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

package ru.job4j.odd.isp.menu;

/**
 *  Класс-реализация MenuPrinter. Печатает меню в консоль с отступами.
 *  Пример:
 *  Задача 1.
 * ---- Задача 1.1.
 * --------- Задача 1.1.1.
 * --------- Задача 1.1.2.
 * ----- Задача 1.2.
 * */
public class ConsoleMenuPrinter implements MenuPrinter {
    private static final int LENGTH_ROOT_NUMB = 2;

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo i : menu) {
            int lengthNumberStr = i.getNumber().length();
            String menuLine = i.getNumber() + i.getName();
            if (lengthNumberStr > LENGTH_ROOT_NUMB) {
                menuLine = dashGenerator(lengthNumberStr) + menuLine;
            }
            System.out.println(menuLine);
        }
    }

    private String dashGenerator(int lengthNumberStr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (lengthNumberStr - LENGTH_ROOT_NUMB) / LENGTH_ROOT_NUMB; i++) {
            sb.append("----");
        }
        return sb.toString();
    }
}

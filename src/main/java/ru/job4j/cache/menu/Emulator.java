package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

/**
 * Класс Emulator для работы с пользователем.
 * Предоставляет пользователю возможности:
 * - указать кэшируемую директорию
 * - загрузить содержимое файла в кэш
 * - получить содержимое файла из кэша
 */
public class Emulator {
    private AbstractCache<String, String> dirFileCache;
    private final Scanner scanner;

    public Emulator(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        System.out.println("""
                   ************  Меню  **************
                1. загрузить содержимое файла в кэш
                2. получить содержимое файла из кэша
                3. выход""");
    }

    public int answers() {
        int answer = 0;
        boolean invalid = true;
        do {
            try {
                answer = Integer.parseInt(scanner.nextLine());
                if (answer >= 1 && answer <= 3) {
                    invalid = false;
                } else {
                    System.out.println("введите число от 1 до 3");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("введите число от 1 до 3");
            }
        } while (invalid);
        return answer;
    }

    public void start() {
        System.out.println("Старт программы =Кэш=\nукажите директорию кэширования: ");
        String path = scanner.nextLine();
        dirFileCache = new DirFileCache(path);
    }

    public String getFile() {
        System.out.println("укажите файл");
        String file = scanner.nextLine();
        return dirFileCache.get(file);
    }

    public void printFile() {
        System.out.println(getFile());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Emulator emulator = new Emulator(scanner);
        emulator.start();
        int answer = 0;
        while (3 != answer) {
            emulator.showMenu();
            answer = emulator.answers();
            if (1 == answer) {
                emulator.getFile();
            }
            if (2 == answer) {
                emulator.printFile();
            }
        }
    }
}

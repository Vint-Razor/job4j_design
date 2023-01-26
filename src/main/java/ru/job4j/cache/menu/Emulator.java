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
    private static final String DIRECTORY = "./data/ConsoleChat";
    private AbstractCache<String, String> dirFileCache;
    private Scanner scanner;

    public Emulator(Scanner scanner) {
        this.scanner = scanner;
    }

    public void menuPrint() {
        System.out.println("""
                   ************  Кэш  ************
                1. указать кэшируемую директорию
                2. загрузить содержимое файла в кэш
                3. получить содержимое файла из кэша
                4. выход
                """);
    }

    public int answers() {
        int answer = scanner.nextInt();
        while (validate(answer)) {
            System.out.println("введите число от 1 до 4");
            answer = scanner.nextInt();
        }
        return answer;
    }

    public void cacheDir() {
        System.out.println("укажите директорию");
        String path = scanner.nextLine();
        dirFileCache = new DirFileCache(path);
        System.out.println("кеширование завершено");
    }

    public void uploadFile() {
        System.out.println("укажите файл");
        String file = scanner.nextLine();
        dirFileCache.get(file);
    }

    public void printFile() {
        System.out.println("укажите файл");
        String file = scanner.nextLine();
        System.out.println(dirFileCache.get(file));
    }

    private boolean validate(int num) {
        return num < 0 || num > 4;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Emulator emulator = new Emulator(scanner);
        String answer;
        do {
            emulator.menuPrint();
            answer = scanner.nextLine();
            if ("1".equals(answer)) {
                emulator.cacheDir();
            }
            if ("2".equals(answer)) {
                emulator.uploadFile();
            }
            if ("3".equals(answer)) {
                emulator.printFile();
            }
        } while (!"4".equals(answer));
    }
}

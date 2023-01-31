package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.io.File;
import java.util.Scanner;

/**
 * Класс Emulator для работы с пользователем.
 * Предоставляет пользователю возможности:
 * - указать кэшируемую директорию
 * - загрузить содержимое файла в кэш
 * - получить содержимое файла из кэша
 */
public class Emulator {
    private static final int LOAD = 1;
    private static final int PRINT = 2;
    private static final int EXIT = 3;
    private static final String INPUT_NUM_1_2_3 = "введите число от 1 до 3";
    private static final String MENU = """
                   ************  Меню  **************
                1. загрузить содержимое файла в кэш
                2. получить содержимое файла из кэша
                3. выход""";
    private AbstractCache<String, String> cache;
    private final Scanner scanner;

    public Emulator(Scanner scanner) {
        this.scanner = scanner;
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
                    System.out.println(INPUT_NUM_1_2_3);
                }
            } catch (NumberFormatException nfe) {
                System.out.println(INPUT_NUM_1_2_3);
            }
        } while (invalid);
        return answer;
    }

    private void validate(String path) {
        File file = new File(path);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s не является папкой", path));
        }
    }

    public void start() {
        System.out.println("Старт программы =Кэш=\nукажите директорию кэширования: ");
        String path = scanner.nextLine();
        validate(path);
        cache = new DirFileCache(path);
    }

    public String getFile() {
        System.out.println("укажите файл");
        String file = scanner.nextLine();
        if (!file.endsWith(".txt")) {
            throw new IllegalArgumentException(
                    String.format("%s файл должен иметь расширение \".txt\"", file));
        }
        return cache.get(file);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Emulator emulator = new Emulator(scanner);
        emulator.start();
        int answer = 0;
        while (EXIT != answer) {
            System.out.println(MENU);
            answer = emulator.answers();
            if (LOAD == answer) {
                emulator.getFile();
            }
            if (PRINT == answer) {
                System.out.println(emulator.getFile());
            }
        }
        System.out.println("выход");
    }
}

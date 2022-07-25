/**
 * 2. Поиск файлов по критерию [#783]
 * <p>
 * 1. Создать программу для поиска файлов. Все классы, относящиеся к этому заданию должны быть
 * в отдельном пакете
 * Важно! Допускается использование ранее созданных вами классов.
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражению(не обязательно).
 * 4. Программа должна собираться в jar и запускаться через
 * java -jar find.jar -d=c:/ -n=*.?xt -t=mask -o=log.txt
 * Ключи
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска:
 * mask искать по маске,
 * name по полному совпадение имени,
 * regex по регулярному выражению.
 * -o - результат записать в файл.
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 *
 * @author Rustam Yumagujin
 * 25.07.2022
 * @version 1.0
 */

package ru.job4j.io.finder;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class FileFinder {

    private static Path directory;
    private static String name;
    private static String type;
    private static File file;
    private static List<Path> pathList;

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        validator(argsName);
        getListPath();
        saveToFile();
    }

    private static void getListPath() {
        Predicate<Path> predicate = null;
        if ("name".equals(type)) {
            predicate = path -> path.toFile().getName().equals(name);
        } else if ("mask".equals(type)) {
            PathMatcher matcher = FileSystems.getDefault()
                    .getPathMatcher("glob:" + name);
            predicate = path -> matcher.matches(path.getFileName());
        } else if ("regex".equals(type)) {
            predicate = path -> path.toFile().getName().matches(name);
        }
        pathList = Search.search(directory, predicate);
    }

    private static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(file)) {
            for (Path path : pathList) {
                writer.println(path);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void validator(ArgsName argsName) {
        directory = Paths.get(argsName.get("d"));
        name = argsName.get("n");
        type = argsName.get("t");
        file = new File(argsName.get("o"));
        String help = "\nНапример: java -jar find.jar -d=c:/ -n=*.?xt -t=mask -o=log.txt\nКлючи \n"
                + "-d - директория, в которой начинать поиск.\n"
                + "-n - имя файла, маска, либо регулярное выражение.\n"
                + "-t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.\n"
                + "-o - результат записать в файл.";
        if (!directory.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format(
                    "%s не является директорией" + help, directory));
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("аргумент -n не может быть пустым" + help);
        }
        if (!"name".equals(type) && !"mask".equals(type) && !"regex".equals(type)) {
            throw new IllegalArgumentException(
                    "аргумент -t должен иметь значения name, mask, regex" + help);
        }
        if (file.toString().isBlank()) {
            throw new IllegalArgumentException("аргумент -o не может быть пустым" + help);
        }

    }
}

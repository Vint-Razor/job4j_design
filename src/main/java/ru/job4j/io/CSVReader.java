package ru.job4j.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {


    public static void handle(ArgsName argsName) throws IOException {
        String file = argsName.get("path");
        csvValidator(file);
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String[] filter = argsName.get("filter").split(",");
        Scanner scan = new Scanner(new File(file));
        ArrayList<String[]> matrix = new ArrayList<>();
        while (scan.hasNextLine()) {
            matrix.add(scan.nextLine().split(delimiter));
        }
        scan.close();
        List<String> headers = List.of(matrix.get(0));
        output(indexesOfHeader(filter, headers), matrix, out);
    }

    private static void output(int[] indexes, List<String[]> matrix, String out) {
        StringBuilder sb = new StringBuilder();
        for (String[] row : matrix) {
            for (int index : indexes) {
                sb.append(row[index]);
                sb.append(";");
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append(System.lineSeparator());
        }
        String rsl = sb.toString();
        if ("stdout".equals(out)) {
            System.out.println(rsl);
        } else {
            pathValidator(out);
            try (PrintWriter output = new PrintWriter(new BufferedOutputStream(
                    new FileOutputStream(out)
            ))) {
                output.print(sb);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int[] indexesOfHeader(String[] find, List<String> headers) {
        int[] indexes = new int[find.length];
        int index = 0;
        for (String str : find) {
            indexes[index++] = headers.indexOf(str);
        }
        return indexes;
    }

    private static void csvValidator(String path) {
        if (!path.endsWith(".csv")) {
            throw new IllegalArgumentException(String.format("%s файл источник должен иеть расширение \".csv\"", path));
        }
        if (!Paths.get(path).toFile().exists()) {
            throw new IllegalArgumentException(String.format("%s файл не существует.", path));
        }
    }

    private static void pathValidator(String out) {
        if (!Paths.get(out).toFile().exists()) {
            throw new IllegalArgumentException(String.format("%s файл не существует.", out));
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("используйте ключи и значения по примеру:"
                    + " -path=file.csv -delimiter=\";\"  -out=stdout -filter=name,age");
        }
        CSVReader.handle(ArgsName.of(args));
    }
}

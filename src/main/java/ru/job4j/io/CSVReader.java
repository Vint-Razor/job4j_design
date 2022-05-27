package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {

    }

    public static void main(String[] args) throws IOException {
        String[] arr = {"name", "age"};
        File file = new File("./data/table.csv");
        System.out.println((headerNames(file, arr)));
    }

    public static List<String> headerNames(File file, String[] find) throws IOException {
        Scanner scan = new Scanner(file);
        ArrayList<String[]> matrix = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        if (scan.hasNextLine()) { // переписать в do while
            headers = Arrays.stream(scan.nextLine()
                            .split(";"))
                    .collect(Collectors.toList());
        }
        while (scan.hasNextLine()) {
            scan.useDelimiter(";");
            matrix.add(scan.nextLine().split(";"));
        }
        matrix.forEach(arr -> System.out.println(Arrays.toString(arr)));
        ////INDEX OF HEADER////////////
        int[] indexes = new int[find.length];
        int i = 0;
        for (String str : find) {
            indexes[i++] = headers.indexOf(str);
        }

        ////// out column /////////////
        System.out.println(Arrays.toString(indexes));
        for (String[] row : matrix) {
            for (int index : indexes) {
                System.out.print(row[index] + ";");
            }
            System.out.println();
        }
        scan.close();
        return headers;
    }
}

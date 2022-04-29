package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class Matrix {
    public static int[][] multiply(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
            }
        }
        return matrix;
    }

    public static void matrixWriteFile(int[][] matrix) {
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int[] row : matrix) {
                out.write(Arrays.toString(row).getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Matrix.matrixWriteFile(multiply(9));
    }
}

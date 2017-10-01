package ru.homework.java;

import java.util.Arrays;

public class Spiral {
    /**
     * print two-dimensional array in spiral circumvention.
     * Throw IllegalArgumentException if matrix has even length.
     *
     * @param matrix - two-dimensional array of int that will be printed.
     * @return string that contain all elements of matrix in their spiral circumvention
     */
    public static String spiral(final int[][] matrix) throws IllegalArgumentException {
        if (matrix.length % 2 == 0) throw new IllegalArgumentException("Even matrix size");
        StringBuilder answer = new StringBuilder();
        int start_pos = matrix.length / 2;
        answer.append(matrix[start_pos][start_pos]);
        int cnt = 1;
        while (cnt < matrix.length / 2 + 1) {
            for (int i = 0; i < cnt * 2; i++) {
                answer.append(matrix[start_pos - cnt + i + 1][start_pos + cnt]);
            }
            for (int i = 0; i < cnt * 2; i++) {
                answer.append(matrix[start_pos + cnt][start_pos + cnt - i - 1]);
            }
            for (int i = 0; i < cnt * 2; i++) {
                answer.append(matrix[start_pos + cnt - i - 1][start_pos - cnt]);
            }
            for (int i = 0; i < cnt * 2; i++) {
                answer.append(matrix[start_pos - cnt][start_pos - cnt + 1 + i]);
            }
            cnt++;
        }
        return answer.toString();
    }

    /**
     * Sort matrix by first element in colon. Elements must be Comparable.
     *
     * @param matrix - two-dimensional array that will be sorted
     */
    public static void sortMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        Arrays.sort(matrix, (int[] o1, int[] o2) -> o2[0] - o1[0]);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    static public void main(String[] st) {

    }
}

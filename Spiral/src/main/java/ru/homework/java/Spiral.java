package ru.homework.java;

import java.util.Arrays;

/**
 * Class for work with two-dimensional arrays. Consist methods spiral() and sort()
 */
public class Spiral {
    /**
     * print two-dimensional array in spiral circumvention.
     *
     * @throws IllegalArgumentException if matrix has even length.
     * @param matrix - two-dimensional array of int that will be printed.
     * @return string that contain all elements of matrix in their spiral circumvention
     */
    public static String spiral(final int[][] matrix) throws IllegalArgumentException {
        if (matrix.length % 2 == 0)
            throw new IllegalArgumentException("Even matrix size");
        StringBuilder answer = new StringBuilder();
        int startPosition = matrix.length / 2;
        answer.append(matrix[startPosition][startPosition]);
        answer.append(' ');
        int cnt = 1;
        while (cnt < matrix.length / 2 + 1) {
            for (int i = 0; i < cnt * 2; i++) {
                answer.append(matrix[startPosition - cnt + i + 1][startPosition + cnt]);
                answer.append(' ');
            }
            for (int i = 0; i < cnt * 2; i++) {
                answer.append(matrix[startPosition + cnt][startPosition + cnt - i - 1]);
                answer.append(' ');
            }
            for (int i = 0; i < cnt * 2; i++) {
                answer.append(matrix[startPosition + cnt - i - 1][startPosition - cnt]);
                answer.append(' ');
            }
            for (int i = 0; i < cnt * 2; i++) {
                answer.append(matrix[startPosition - cnt][startPosition - cnt + 1 + i]);
                answer.append(' ');
            }
            cnt++;
        }
        return answer.toString();
    }

    private static void transposeMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
    /**
     * Sort matrix by first element in colon. Elements must be Comparable.
     *
     * @param matrix - two-dimensional array that will be sorted
     */
    public static void sortMatrix(int[][] matrix) {
        transposeMatrix(matrix);
        Arrays.sort(matrix, (int[] o1, int[] o2) -> o2[0] - o1[0]);
        transposeMatrix(matrix);
    }
}

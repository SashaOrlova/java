package ru.homework.java;

import static org.junit.Assert.assertEquals;

public class SpiralTest {
    /**
     * test how Spiral works with different size of matrix. Include tests for methods spiral and sort.
     */
    @org.junit.Test
    public void spiralTestSimple() {
        int[][] m = new int[1][1];
        int c = 1;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
                m[i][j] = c++;
            }
        }
        assertEquals("1 ", Spiral.spiral(m));
    }

    @org.junit.Test
    public void spiralTestThree() {
        int[][] m = new int[3][3];
        int c = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m[i][j] = c++;
            }
        }
        assertEquals("5 6 9 8 7 4 1 2 3 ", Spiral.spiral(m));
    }

    @org.junit.Test
    public void spiralTestFive() {
        int[][] m = new int[5][5];
        int c = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                m[i][j] = c++;
            }
        }
        assertEquals("13 14 19 18 17 12 7 8 9 10 15 20 25 24 23 22 21 16 11 6 1 2 3 4 5 ", Spiral.spiral(m));
    }

    @org.junit.Test
    public void spiralTestEvenSize() {
        int[][] m = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                m[i][j] = 0;
            }
        }
        try {
            Spiral.spiral(m);
        } catch (IllegalArgumentException thrown) {
            assertEquals("Even matrix size", thrown.getMessage());
        }
    }

    /**
     * sort matrix size [1x1]
     */
    @org.junit.Test
    public void spiralTestSimpleSort() {
        int[][] m = new int[1][1];
        int c = 1;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
                m[i][j] = c++;
            }
        }
        Spiral.sortMatrix(m);
        assertEquals(m[0][0], 1);
    }

    /**
     * sort matrix size [5x5] full numbers from 1 to 25
     */
    @org.junit.Test
    public void spiralTestSort() {
        int[][] m = new int[5][5];
        int c = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                m[i][j] = c++;
            }
        }
        Spiral.sortMatrix(m);
        int[][] n = new int[5][5];
        for (int i = 0; i < 5; i++) {
            c = (i + 1) * 5;
            for (int j = 0; j < 5; j++) {
                n[i][j] = c - j;
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(m[i][j], n[i][j]);
            }
        }
    }
}